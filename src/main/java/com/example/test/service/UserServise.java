package com.example.test.service;

import lombok.*;

import com.example.test.repository.UserRepository;
import com.example.test.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServise {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(String name, Integer age, String email, String uri){
        User user = new User();

        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setUri(uri);

        userRepository.save(user);

        return user;
    }

    public void getInfo(Long id, Model model){

        Optional<User> user = userRepository.findById(id);

        model.addAttribute("id", user.get().getId());
        model.addAttribute("name", user.get().getName());
        model.addAttribute("age", user.get().getAge());
        model.addAttribute("email", user.get().getEmail());
        model.addAttribute("uri", user.get().getUri());
        model.addAttribute("user", user.get());
    }

    public Boolean newStatus(Long id, Boolean status){
        Boolean res = true;
        Optional<User> userOp = userRepository.findById(id);
        User user = userOp.get();
        if (status != user.getStatus())
        {
            user.setStatus(status);
            userRepository.save(user);
        }
        else
        {
            res = false;
        }
        return res;
    }

    public List<User> getStatStatus(String stat){
        List<User> users;

        if (stat.equals("online"))
        {
            users = userRepository.findByStatus(true);
        }
        else
        {
            users = userRepository.findByStatus(false);
        }
        return users;
    }

    public List<User> getTimeStat (Timestamp timestamp){
        return userRepository.findByTimestampGreaterThan(timestamp);
    }

    public List<User> getTimeAndStatusStat(Timestamp timestamp, String stat){
        List<User> users;
        if (stat.equals("online"))
        {
            users = userRepository.findByTimestampGreaterThanAndStatus(timestamp,true);
        }
        else
        {
            users = userRepository.findByTimestampGreaterThanAndStatus(timestamp,false);
        }
        return users;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void getStatus(String timestamp, String status, Model model){
        if(timestamp.isEmpty())
        {
            if(status.isEmpty())
            {
                model.addAttribute("users", getUsers());
            }
            else
            {
                if (status.equals("online") || status.equals("offline"))
                {
                    model.addAttribute("users", getStatStatus(status));
                }
                else
                {
                    model.addAttribute("error", "Некорректно введён статус");
                }
            }
        }
        else
        {
            if(status.isEmpty())
            {
                try
                {
                    model.addAttribute("users", getTimeStat(Timestamp.valueOf(timestamp)));
                }
                catch (IllegalArgumentException e)
                {
                    model.addAttribute("error", "Неверный формат даты");
                }
            }
            else
            {
                if (status.equals("online") || status.equals("offline"))
                {
                    try
                    {
                        model.addAttribute("users", getTimeAndStatusStat(Timestamp.valueOf(timestamp),status));
                    }
                    catch (IllegalArgumentException e)
                    {
                        model.addAttribute("error", "Неверный формат даты");
                    }
                }
                else
                {
                    model.addAttribute("error", "Некорректно введён статус");
                }
            }
        }

    }

    public void updStatus(Long id, String status, Model model){
        String error ="Некорректно введён статус";
        switch (status){
            case "online":
                if (newStatus(id,true))
                {
                    model.addAttribute("newStatus", "online");
                    model.addAttribute("oldStatus", "offline");
                    model.addAttribute("id", id);
                }
                else
                {
                    error = "Упользователя уже установленн данный статус";
                    model.addAttribute("error", error);
                }
                break;
            case "offline":
                if (newStatus(id,false))
                {
                    model.addAttribute("newStatus", "offline");
                    model.addAttribute("oldStatus", "online");
                    model.addAttribute("id", id);
                }
                else
                {
                    error = "Упользователя уже установленн данный статус";
                    model.addAttribute("error", error);
                }
                break;
            default:
                model.addAttribute("error", error);
                break;
        }
    }
}

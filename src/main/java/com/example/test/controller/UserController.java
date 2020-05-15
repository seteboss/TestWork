package com.example.test.controller;


import com.example.test.service.UserServise;
import com.example.test.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class UserController {

    @Autowired
    private UserServise userServise;

    @GetMapping("/addUser")
    private String LoadPige(){ return "addUser";}

    @PostMapping ("/addUser")
    private String AddUser (@RequestParam String name,
                            @RequestParam Integer age,
                            @RequestParam String email,
                            @RequestParam String uri,
                            Model model){
        User user = userServise.saveUser(name,age,email,uri);

        model.addAttribute("id", user.getId());
        return "addUser";
    }




}

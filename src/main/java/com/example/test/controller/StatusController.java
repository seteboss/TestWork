package com.example.test.controller;


import com.example.test.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatusController {

    @Autowired
    private UserServise userServise;

    @GetMapping("/updStatusUser")
    public String loadPage(){ return "/updStatusUser"; }


    @PostMapping("/updStatusUser")
    public String UpdStatus(@RequestParam Long id,
                            @RequestParam String status,
                            Model model){
        userServise.updStatus(id, status, model);
        return "/updStatusUser";
    }



}

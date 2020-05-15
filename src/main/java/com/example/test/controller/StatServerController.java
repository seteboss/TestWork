package com.example.test.controller;

import com.example.test.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class StatServerController {

    @Autowired
    private UserServise userServise;

    @GetMapping("/statServer")
    public String loadPage(){
        return "statServer";
    }

    @PostMapping("/statServer")
    public String Stats(@RequestParam String timestamp,
                           @RequestParam String status,
                           Model model){

        userServise.getStatus(timestamp,status,model);

        return "statServer";
    }

}

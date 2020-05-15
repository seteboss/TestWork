package com.example.test.controller;


import com.example.test.service.UserServise;
import com.example.test.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetUserController {

    @Autowired
    private UserServise userServise;

    @GetMapping("/getUserInfo")
    public String loadPage(){return "/getUserInfo";}

    @PostMapping("/getUserInfo")
    public String getInfo(@RequestParam Long id, Model model){

      userServise.getInfo(id, model);

      return "getUserInfo";
    }


}

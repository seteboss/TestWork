package com.example.test.controller;


import com.example.test.service.FileLoaderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Controller
public class LoaderController {

    @Autowired
    private FileLoaderServise fileLoaderServise;


    @GetMapping("/loader")
    public String loadPage(){
        return "/loader";
    }

    @PostMapping("/loader")
    public String laod(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        fileLoaderServise.loadFile(file, model);

        return "/loader";
    }


}

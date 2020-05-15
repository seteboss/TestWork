package com.example.test.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
public class FileLoaderServise {

    @Value("${upload.path}")
    private String uploadPath;


    public void loadFile(MultipartFile file, Model model) throws IOException {

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            File test = new File(uploadPath + "/" + resultFilename);
            file.transferTo(test);

            URI uri = test.toURI();
            model.addAttribute("URI", uri);
        }
    }

}

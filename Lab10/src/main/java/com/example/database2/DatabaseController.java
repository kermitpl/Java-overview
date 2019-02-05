package com.example.database2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController{

    @RequestMapping("/")
    public String appWorking() {
        return "App is working.";
    }

}

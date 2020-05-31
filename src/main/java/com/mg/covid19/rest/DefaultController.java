package com.mg.covid19.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private Environment env;


    @GetMapping
    public String healthChecker(){
        return env.getProperty("spring.application.name")+" project is running";
    }
}

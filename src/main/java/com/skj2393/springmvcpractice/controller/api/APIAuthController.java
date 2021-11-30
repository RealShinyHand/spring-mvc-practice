package com.skj2393.springmvcpractice.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api")
@RestController
public class APIAuthController {

    @GetMapping("/sign-up")
    public String signUp(){
        return "가입 했다.";
    }

    @GetMapping("/login")
    public String login(){
        return "done";
    }
}

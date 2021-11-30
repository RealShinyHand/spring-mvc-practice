package com.skj2393.springmvcpractice.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController implements ErrorController {
    @GetMapping("/")
    public String root(){
        return "index";
    }

    //ErrorController 인터페이스 구현해줘야 error 페이지가 오류없이 작동 ,
    //엡 프로퍼티 파일에서도 설정 추가해야함 .
    @RequestMapping("/error-custom")
    public String error(){
        return "error";
    }
}

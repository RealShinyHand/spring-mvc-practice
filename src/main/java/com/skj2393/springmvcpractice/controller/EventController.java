package com.skj2393.springmvcpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/events")
@Controller
public class EventController {
    @GetMapping("/")
    public String place(){
        return "/event/index";
    }
    @GetMapping("/{eventId}")
    public String placeDetail(@PathVariable Integer placeId){
        return "/event/detail";
    }
}

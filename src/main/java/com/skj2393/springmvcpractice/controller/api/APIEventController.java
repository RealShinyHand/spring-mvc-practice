package com.skj2393.springmvcpractice.controller.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/event")
    public List<String> getEvents(){
        return Collections.unmodifiableList(Arrays.asList("event1","event2"));
    }

    @PostMapping("/event")
    public Boolean createEvent(){
        return true;
    }


    @GetMapping("/event/{eventId}")
    public String getEvent(@PathVariable Integer eventId){
        return String.format("place + %s",eventId);
    }

    @PutMapping("/event/{placeId}")
    public Boolean modifyEvent(@PathVariable Integer eventId){
        return true;
    }

    @DeleteMapping("/event/{eventId}")
    public Boolean deleteEvent(@PathVariable Integer eventId){
        return true;
    }
}

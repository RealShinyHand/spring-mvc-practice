package com.skj2393.springmvcpractice.controller;

import com.skj2393.springmvcpractice.controller.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @GetMapping("/places")
    public ModelAndView adminPlaces(@RequestParam(required = false)PlaceType placeType,
                                    @RequestParam(required = false)String placeName,
                                    @RequestParam(required = false)String address){
        Map<String,Object> map = new HashMap<>();
        map.put("placeType",placeType);
        map.put("placeName",placeName);
        map.put("address",address);
        return new ModelAndView("admin/places",map);
    };
    @GetMapping("/places/{placeId}")
    public String adminPlaceDetail(@PathVariable Integer placeId){ //webMVCAutoConfiguration에서 타입 매칭
        return "admin/places-detail";
    };
    @GetMapping("/events}")
    public String adminEvents(){
        return "admin/events";
    };
    @GetMapping("/events/{eventId}")
    public String adminEventDetail(@PathVariable Integer eventId){
        return "admin/events-detail";
    };
}

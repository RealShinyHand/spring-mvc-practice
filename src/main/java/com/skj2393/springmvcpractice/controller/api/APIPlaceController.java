package com.skj2393.springmvcpractice.controller.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@RequestMapping("/api")
//@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public List<String> getPlaces(){
        return Collections.unmodifiableList(Arrays.asList("place1","place2"));
    }

    @PostMapping("/places")
    public Boolean createPlace(){
        return true;
    }


    @GetMapping("/places/{placeId}")
    public String getPlace(@PathVariable Integer placeId){
        return String.format("place + %s",placeId);
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable Integer placeId){
        return true;
    }
}

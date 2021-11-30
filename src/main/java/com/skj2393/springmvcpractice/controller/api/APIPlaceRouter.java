package com.skj2393.springmvcpractice.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.web.servlet.function.RouterFunctions.route;
@Configuration
public class APIPlaceRouter {

    @Bean
    public RouterFunction<ServerResponse> placeRouter(){
        return route().GET("/api/places",(req)->{
        return ServerResponse.ok().body(Collections.unmodifiableList(Arrays.asList("place1","place2")) );
        }).POST("/api/places",(req)->{
            return ServerResponse.ok().body(true);
        }).GET("/api/places/{placeId}",(req)->{
            return ServerResponse.ok().body( "place: " + req.pathVariable("placeId"));
        }).PUT("/api/places",(req)->{
            return ServerResponse.ok().body(true) ;
        }).DELETE("/api/places",(req)->{
            return ServerResponse.ok().body(true );
        }).build();
    }
}

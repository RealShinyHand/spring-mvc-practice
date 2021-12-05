package com.skj2393.springmvcpractice.controller.api;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.dto.APIErrorResponse;
import com.skj2393.springmvcpractice.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/event")
    public List<String> getEvents() throws HttpRequestMethodNotSupportedException {
        throw new HttpRequestMethodNotSupportedException("스프링 405 에러 테스트 ");
       // return Collections.unmodifiableList(Arrays.asList("event1","event2"));
    }

    @PostMapping("/event")
    public Boolean createEvent(){
        throw  new RuntimeException("런타임 에러");
        //return true;
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
    //글로벌 하게 제작했으니깐 주석처리
//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e){
//        ErrorCode ec = e.getErrorCode();
//        HttpStatus status = ec.isClientSideError() ? HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity.status(status).body(APIErrorResponse.of(false,ec,ec.getMessage(e)));
//    }
}

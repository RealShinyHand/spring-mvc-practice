package com.skj2393.springmvcpractice.controller.api;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.controller.constant.EventStatus;
import com.skj2393.springmvcpractice.controller.service.EventService;
import com.skj2393.springmvcpractice.dto.APIDataResponse;
import com.skj2393.springmvcpractice.dto.APIErrorResponse;
import com.skj2393.springmvcpractice.dto.EventDTO;
import com.skj2393.springmvcpractice.dto.EventResponse;
import com.skj2393.springmvcpractice.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @RequestParam(required = false) Long placeId,
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDateTime eventEndDatetime
    ) {
        List<EventResponse> responses = eventService.
                getEvent(placeId,eventName,eventStatus,
                        eventStartDatetime,eventEndDatetime)
                .stream().map(EventResponse::from).toList();
        return APIDataResponse.of(responses);
    }

    @PostMapping("/event")
    public Boolean createEvent(){
        throw  new RuntimeException("????????? ??????");
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
    //????????? ?????? ?????????????????? ????????????
//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e){
//        ErrorCode ec = e.getErrorCode();
//        HttpStatus status = ec.isClientSideError() ? HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity.status(status).body(APIErrorResponse.of(false,ec,ec.getMessage(e)));
//    }
}

package com.skj2393.springmvcpractice.controller.domain;

import com.skj2393.springmvcpractice.controller.constant.PlaceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Place {
    private Long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private String memo;
    private Integer capacity;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

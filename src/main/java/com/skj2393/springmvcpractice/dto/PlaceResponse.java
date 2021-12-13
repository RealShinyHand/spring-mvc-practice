package com.skj2393.springmvcpractice.dto;

import com.skj2393.springmvcpractice.controller.constant.PlaceType;

public record PlaceResponse(

         PlaceType placeType,
         String placeName,
         String address,
         String phoneNumber,
         String memo,
         Integer capacity

) {
    public static PlaceResponse of(PlaceType placeType, String placeName, String address, String phoneNumber, String memo, Integer capacity) {
        return new PlaceResponse( placeType,  placeName,  address,  phoneNumber,  memo,  capacity);
    }
}

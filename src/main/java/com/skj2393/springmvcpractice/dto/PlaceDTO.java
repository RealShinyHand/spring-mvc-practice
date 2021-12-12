package com.skj2393.springmvcpractice.dto;

import com.skj2393.springmvcpractice.controller.constant.PlaceType;

public record PlaceDTO(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        String memo,
        Integer capacity
) {
    public static PlaceDTO of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            String memo,
            Integer capacity
    ) {
        return new PlaceDTO(placeType,
                placeName,
                address,
                phoneNumber,
                memo,
                capacity);
    }


}

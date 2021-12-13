package com.skj2393.springmvcpractice.controller.api;

import com.skj2393.springmvcpractice.controller.constant.PlaceType;
import com.skj2393.springmvcpractice.dto.APIDataResponse;
import com.skj2393.springmvcpractice.dto.PlaceDTO;
import com.skj2393.springmvcpractice.dto.PlaceResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                "신장개업",
                30
        )));
    }

    @PostMapping("/places")
    public Boolean createPlace() {
        return true;
    }


    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId) {

        if(placeId.equals(2)){
            return APIDataResponse.of(null);
        }
        return APIDataResponse.of(PlaceDTO.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                "신장개업",
                30
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable Integer placeId) {
        return true;
    }
}

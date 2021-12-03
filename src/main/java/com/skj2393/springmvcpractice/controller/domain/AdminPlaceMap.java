package com.skj2393.springmvcpractice.controller.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data //겟터ㅡ셋터, 생성자, 투스트링 , 해시이퀄
public class AdminPlaceMap {
    private Long id;
    private Long adminId;
    private Long PlaceId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}

package com.skj2393.springmvcpractice.controller.repository;


import com.skj2393.springmvcpractice.controller.constant.EventStatus;
import com.skj2393.springmvcpractice.controller.domain.Event;
import com.skj2393.springmvcpractice.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// TODO: 디폴트 나중에 뺴셈  , 구현할 때 삭제하자
public interface EventRepository {
    //리스트, 단건, 생성, 수정, 삭제

    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
            ){
        return List.of();
    }

    default Optional<EventDTO> findEvent(Long placeId){
        return Optional.empty();
    }
    default boolean insertEvent(EventDTO eventDTO){return false;}

    default  boolean updateEvent(Long eventId,EventDTO dto){
        return false;
    }

    default  boolean deleteEvent(Long eventId){return false;}
}

package com.skj2393.springmvcpractice.controller.service;

import com.skj2393.springmvcpractice.controller.constant.EventStatus;
import com.skj2393.springmvcpractice.controller.repository.EventRepository;
import com.skj2393.springmvcpractice.dto.EventDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks private EventService sut;
    @Mock private EventRepository eventRepository;


    @DisplayName("검색 조건 없이 이벤트 검색 시 , 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNoting_whenSearchingEvents_thenResultEventList(){
        //given

        given(eventRepository.findEvents(null,null,null,null,null))
                .willReturn(
                        List.of(
                                createEventDTO(1L,"오전 운동",true),
                                createEventDTO(1L,"오후 운동",false)
                        )
                );


        //when
        List<EventDTO> eventList=  sut.getEvent(null,null,null,null,null);
        //then
        assertThat(eventList).hasSize(2);
        verify(eventRepository).findEvents(null,null,null,null,null);
    }


    @DisplayName("검색 조건 이벤트 검색 시 , 검색된 결과를 출력하여 보여준다.")
    @Test
    void givenSearchParam_whenSearchingEvents_thenResultEventList(){
        //given
        Long placeId = 1L;
        String eventName = "오전 운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDatetime = LocalDateTime.of(2021,1,1,0,0);
        LocalDateTime eventEndDatetime =  LocalDateTime.of(2021,1,2,0,0);
        given(eventRepository.findEvents(placeId,eventName,eventStatus,eventStartDatetime,eventEndDatetime))
                .willReturn(List.of(createEventDTO(1L,"오전 운동",eventStatus,eventStartDatetime,eventEndDatetime)));

        //when
        List<EventDTO> eventList=  sut.getEvent(placeId,eventName,eventStatus,eventStartDatetime,eventEndDatetime);
        //then
//        assertThat(eventList).hasSize(1)
//        .allSatisfy(event ->{
//            assertThat(event).hasFieldOrPropertyWithValue("placeId",placeId).
//                    hasFieldOrPropertyWithValue("eventName",eventName).
//                    hasFieldOrPropertyWithValue("eventStatus",eventStatus);
//            assertThat(event.eventStartDatetime()).isAfterOrEqualTo(eventStartDatetime).
//            isBeforeOrEqualTo(eventStartDatetime);
//        });
        then(eventRepository).should().findEvents(placeId,eventName,eventStatus,eventStartDatetime,eventEndDatetime);
    }

    @DisplayName("이벤트 ID로 존재하는 이벤트를 조회하면, 해당 이벤트 정보를 출력하여 보여준다.")
    @Test
    void givenEventId_whenSearchingExistingEvent_thenReturnsEvent() {
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDTO(1L, "오전 운동", true);
        given(eventRepository.findEvent(eventId)).willReturn(Optional.of(eventDTO));
        //when
        Optional<EventDTO> result =  sut.getEvent(eventId);
        //Then
        assertThat(result).hasValue(eventDTO);
        then(eventRepository).should().findEvent(eventId);

    }

    @DisplayName("이벤트 ID로 존재하지 않는 이벤트를 조회하면, 빈 정보 출력")
    @Test
    void givenEventId_whenSearchingNonexistentEvent_thenReturnsEmptyOptional() {
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDTO(1L, "오전 운동", true);
        given(eventRepository.findEvent(eventId)).willReturn(Optional.empty());
        //when
        Optional<EventDTO> result =  sut.getEvent(eventId);
        //Then
        assertThat(result).isEmpty();
        then(eventRepository).should().findEvent(eventId);

    }
    
    @DisplayName("이벤트 정보를 주면 이벤트를 생성하고 결과를 true로 반환")
    @Test
    void givenEvent_whenCreateEvent_thenCreateEventAndReturnsTrue() {
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDTO(1L, "오전 운동", true);
        given(eventRepository.insertEvent(eventDTO)).willReturn(true);
        //when
        boolean result =  sut.createEvent(eventDTO);
        //Then
        assertThat(result).isTrue();
        then(eventRepository).should().insertEvent(eventDTO);

    }

    @DisplayName("이벤트 정보를 안주면 이벤트를 생성 중단 결과를 false로 반환")
    @Test
    void givenNothing_whenCreateEvent_thenAbortCreatingAndReturnsFalse() {
        // Given
        given(eventRepository.insertEvent(null)).willReturn(false);
        //when
        boolean result =  sut.createEvent(null);
        //Then
        assertThat(result).isFalse();
        then(eventRepository).should().insertEvent( null);

    }

    @DisplayName("이벤트 ID와 정보를 주면, 이벤트 정보를 변경하고 결과를 true 로 보여준다.")
    @Test
    void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue() {
        // Given
        long eventId = 1L;
        EventDTO dto = createEventDTO(1L, "오후 운동", false);

        given(eventRepository.updateEvent(eventId,dto)).willReturn(true);
        // When
        boolean result = sut.modifyEvent(eventId, dto);

        // Then
        assertThat(result).isTrue();
        then(eventRepository).should().updateEvent(eventId,dto);
    }

    @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 변경 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        long eventId = 1L;
        EventDTO dto = createEventDTO(1L, "오후 운동", false);

        given(eventRepository.updateEvent(null,dto)).willReturn(false);

        // When
        boolean result = sut.modifyEvent(null, dto);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(null,dto);
    }

    @DisplayName("이벤트 ID만 주고 변경할 정보를 주지 않으면, 이벤트 정보 변경 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        long eventId = 1L;
        given(eventRepository.updateEvent(eventId,null)).willReturn(false);
        // When
        boolean result = sut.modifyEvent(eventId, null);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(eventId,null);
    }

    @DisplayName("이벤트 ID를 주면, 이벤트 정보를 삭제하고 결과를 true 로 보여준다.")
    @Test
    void givenEventId_whenDeleting_thenDeletesEventAndReturnsTrue() {
        // Given
        long eventId = 1L;
        given(eventRepository.deleteEvent(eventId)).willReturn(true);
        // When
        boolean result = sut.removeEvent(eventId);

        // Then
        assertThat(result).isTrue();
        then(eventRepository).should().deleteEvent(eventId);
    }

    @DisplayName("이벤트 ID를 주지 않으면, 삭제 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenDeleting_thenAbortsDeletingAndReturnsFalse() {
        // Given
        given(eventRepository.deleteEvent(null)).willReturn(false);
        // When
        boolean result = sut.removeEvent(null);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().deleteEvent(null);
    }

    private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "12" : "16";

        return createEventDTO(
                placeId,
                eventName,
                EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourStart)),
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourEnd))
        );
    }

    private EventDTO createEventDTO(
            long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return EventDTO.of(
                placeId,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                0,
                24,
                "마스크 꼭 착용하세요",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
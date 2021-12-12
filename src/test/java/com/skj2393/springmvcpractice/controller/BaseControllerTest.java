package com.skj2393.springmvcpractice.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@TestConstrictor(autowiredMode = AutowiredMode.ALL) 생성자에 있는 모든 파라메터에 스프링 컨테이너가 주도권을 가져가서 오토 와이어 할려고 시도함
@AutoConfigureMockMvc
@SpringBootTest
class BaseControllerTest {
    @Autowired
    private MockMvc mvc;

    private final MockMvc mvc3;

    @Test
    void testRoot() throws Exception {
        //given


        //when & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(Matchers.containsString("index Page")))
                .andExpect(view().name("index"))
                .andDo(print());

//
//        //given
//
//        //when
//        ResultActions ra = mvc.perform(get("/"));
//        // Then
//        ra.andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(content().string(Matchers.containsString("index Page")))
//                .andExpect(view().name("index"))
//                .andDo(print());
    }
    // juint5에서 지원하는 디펜던시 주입방식
    void givenNoting_whenRequestingRootPage_thenReturnsIndexPage(@Autowired MockMvc mvc2) throws Exception{
        //when & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(Matchers.containsString("index Page")))
                .andExpect(view().name("index"))
                .andDo(print());

    }
    //@Autowired 메소드에 부여해도 됨
    public BaseControllerTest(@Autowired MockMvc mvc3){
        this.mvc3 = mvc3;
    }



}
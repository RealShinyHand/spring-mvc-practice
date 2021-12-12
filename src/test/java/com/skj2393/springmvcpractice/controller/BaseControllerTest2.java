package com.skj2393.springmvcpractice.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BaseController.class)
class BaseControllerTest2 {
    private final MockMvc mvc;

    public BaseControllerTest2(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("[View] [Get] 기본 페이지 요청")
    @Test
    void givenNoting_whenRequestRootPage_thenReturnIndexPage() throws Exception{
        mvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("index Page")))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

}

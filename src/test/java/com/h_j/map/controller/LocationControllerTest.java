package com.h_j.map.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocationControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void before(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("좌표 검색")
    void getCoordinate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/result_coordinate?location=서울"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("길찾기 (서울 - 부산)")
    void findPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/result_path?departure=서울&destination=부산"))
                .andExpect(status().isOk());
    }
}
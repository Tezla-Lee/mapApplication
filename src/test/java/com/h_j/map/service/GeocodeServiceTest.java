package com.h_j.map.service;

import com.h_j.map.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeocodeServiceTest {

    @Autowired
    private GeocodeService geocodeService;

    @Test
    void updateGeo() {
        LocationDto locationDto = new LocationDto();
        locationDto.setAddress("서울");

        geocodeService.updateGeo(locationDto);

        assertThat(locationDto.getLatitude()).isEqualTo("37.5666102");
        assertThat(locationDto.getLongitude()).isEqualTo("126.9783881");
    }
}
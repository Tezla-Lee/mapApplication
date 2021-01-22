package com.h_j.map.service;

import com.h_j.map.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Directions5ServiceTest {

    @Autowired
    private Directions5Service directions5Service;

    @Test
    void getDirections5() {
        // 출발
        LocationDto departure = new LocationDto();
        departure.setLongitude("127.1058342");
        departure.setLatitude("37.359708");

        // 도착
        LocationDto destination = new LocationDto();
        destination.setLongitude("129.075986");
        destination.setLatitude("35.179470");

        String[] result = directions5Service.getDirections5(departure, destination);

        assertThat(result).isNotEqualTo(null);
    }
}
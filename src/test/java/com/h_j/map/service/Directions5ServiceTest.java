package com.h_j.map.service;

import com.h_j.map.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Directions5ServiceTest {

    @Mock
    private Directions5Service directions5Service;

    @Test
    public void getDirections5() {
        // 출발
        LocationDto departure = new LocationDto();
        departure.setLongitude("127.1058342");
        departure.setLatitude("37.359708");

        // 도착
        LocationDto destination = new LocationDto();
        destination.setLongitude("129.075986");
        destination.setLatitude("35.179470");

        directions5Service.getDirections5(departure, destination);
    }
}
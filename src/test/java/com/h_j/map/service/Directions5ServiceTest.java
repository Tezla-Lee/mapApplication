package com.h_j.map.service;

import com.h_j.map.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(controllers = Directions5Service.class)
@RequiredArgsConstructor
class Directions5ServiceTest {

    @MockBean
    private Directions5Service directions5Service;

    @Test
    public void getDirections5() {
        LocationDto departure = new LocationDto();
        departure.setLongitude("127.1058342");
        departure.setLatitude("37.359708");
        LocationDto destination = new LocationDto();
        destination.setLongitude("129.075986");
        destination.setLatitude("35.179470");
        directions5Service.getDirections5(departure, destination);
    }
}
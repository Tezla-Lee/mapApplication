package com.h_j.map.controller;

import com.h_j.map.model.Location;
import com.h_j.map.service.GeocodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/map")
@RequiredArgsConstructor
public class LocationController {
    private GeocodeService geocodeService;

    @GetMapping("/pathfinder")
    public String findPath(HttpServletRequest request, String arrival, String destination) {
        Location start = new Location(arrival);
        Location end = new Location(destination);

        geocodeService.updateGeo(start);
        geocodeService.updateGeo(end);

        request.setAttribute("arrival", start);
        request.setAttribute("destination", end);

        return "result";
    }
}
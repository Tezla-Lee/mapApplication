package com.h_j.map.controller;

import com.h_j.map.dto.LocationDto;
import com.h_j.map.service.Directions5Service;
import com.h_j.map.service.GeocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LocationController {
    @Autowired
    private GeocodeService geocodeService;

    @Autowired
    private Directions5Service directions5Service;

    @PostMapping("/setGeo")
    public String setGeo(Model model, HttpServletRequest request) {
        LocationDto departure = new LocationDto();
        departure.setAddress((String) request.getAttribute("departure"));
        LocationDto destination = new LocationDto();
        destination.setAddress((String) request.getAttribute("destination"));

        geocodeService.updateGeo(departure);
        geocodeService.updateGeo(destination);

        String[] result = directions5Service.getDirections5(departure, destination);


        model.addAttribute("duration", result[0]);
        model.addAttribute("distance", result[1]);

        System.out.println(">>> setGeo");

        return "findway";
    }

    @GetMapping("/setGeo")
    public String setGeo(String start, String end) {
        String distance = start;
        String duration = end;

        System.out.println(distance);
        System.out.println(duration);

        return "findway";
    }

    @GetMapping("/homepage")
    public String home() {
        System.out.println("home");
        return "findway";
    }
}
package com.h_j.map.controller;

import com.h_j.map.dto.LocationDto;
import com.h_j.map.service.Directions5Service;
import com.h_j.map.service.GeocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {
    @Autowired
    private GeocodeService geocodeService;
    @Autowired
    private Directions5Service directions5Service;

    @GetMapping("/result_path")
    public String setGeo(String departure, String destination, Model model) {
        LocationDto start = new LocationDto();
        start.setAddress(departure);
        LocationDto end = new LocationDto();
        end.setAddress(destination);

        geocodeService.updateGeo(start);
        geocodeService.updateGeo(end);

        if (start.getLongitude() == null || start.getLatitude() == null) {
            return "redirect:/findpath";
        }

        String[] result = directions5Service.getDirections5(start, end);

        if (result == null || result[0] == null || result[1] == null) {
            return "redirect:/findpath";
        }

        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("duration", result[0]);
        model.addAttribute("distance", result[1]);

        return "getInfo";
    }

    @GetMapping("/result_coordinate")
    public String setGeo(String location, Model model) {
        LocationDto locationDto = new LocationDto();
        locationDto.setAddress(location);

        geocodeService.updateGeo(locationDto);

        if (locationDto.getLongitude() == null || locationDto.getLatitude() == null) {
            return "redirect:/findpath";
        }

        model.addAttribute("coordinate", locationDto);

        return "getInfo";
    }

    @GetMapping("/findpath")
    public String home() {
        return "getInfo";
    }
}
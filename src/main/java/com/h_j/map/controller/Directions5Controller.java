package com.h_j.map.controller;

import com.h_j.map.dto.LocationDto;
import com.h_j.map.service.Directions5Service;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Directions5Controller {
    private final Directions5Service directions5Service;

    @RequestMapping("/findPath")
    public String findPath(Model model) {
        LocationDto departure = (LocationDto) model.getAttribute("departure");
        LocationDto destination = (LocationDto) model.getAttribute("destination");

        String[] result = directions5Service.getDirections5(departure, destination);

        model.addAttribute("duration", result[0]);
        model.addAttribute("distance", result[1]);

        return "/setGeo";
    }
}
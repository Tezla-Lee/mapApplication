package com.h_j.map.controller;

import com.h_j.map.model.Location;
import com.h_j.map.service.GeocodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final GeocodeService geocodeService;

    @PostMapping("/map/setGeo")
    public String setGeo(Model model, HttpServletRequest request) {
        Location departure = new Location((String) request.getAttribute("departure"));
        Location destination = new Location((String) request.getAttribute("destination"));

        geocodeService.updateGeo(departure);
        geocodeService.updateGeo(destination);

        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);

        return "/map/findPath";

    }

    @GetMapping("/map/setGeo")
    public String setGeo(Model model) {
        String distance = (String) model.getAttribute("distance");
        String duration = (String) model.getAttribute("duration");

        return "findpath";
    }

    @GetMapping("/homepage")
    public String home() {

        return "findpath";
    }
}


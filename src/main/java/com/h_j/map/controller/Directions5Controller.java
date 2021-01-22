package com.h_j.map.controller;

import com.h_j.map.dto.LocationDto;
import com.h_j.map.service.Directions5Service;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Directions5Controller {
    private final Directions5Service directions5Service;

    @GetMapping("/map/findPath")
    public String findPath(Model model) {
        LocationDto departure = (LocationDto) model.getAttribute("departure");
        LocationDto destination = (LocationDto) model.getAttribute("destination");

        JSONObject object = directions5Service.getDirections5(departure, destination);

        JSONObject jsonObject = (JSONObject) object.get("summary");
        JSONArray jsonArray = (JSONArray)jsonObject.get("addresses");

        String duration = null;
        String distance = null;

        for(int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
            if(null != jsonObject2.get("duration")) {
                duration = jsonObject2.get("duration").toString();
            }
            if(null != jsonObject2.get("distance")) {
                distance = jsonObject2.get("distance").toString();
            }
        }

        model.addAttribute("duration", duration);
        model.addAttribute("distance", distance);

        return "redirect:/map/setGeo";
    }
}
package com.h_j.map.service;

import com.h_j.map.dto.LocationDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Directions5Service {

    public String[] getDirections5(LocationDto departure, LocationDto destination) {
        String clientId = "9bdec1tgmw";  //clientId
        String clientSecret = "UOEzYHQGVrBh7PDty5kWKTrELIebbFwyWTEYYLRP";  //clientSecret

        try {
            String api = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=" +
                    departure.getLongitude() + "," + departure.getLatitude() +
                    "&goal=" + destination.getLongitude() + "," + destination.getLatitude();

            StringBuffer sb = new StringBuffer();

            URL url = new URL(api);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            http.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            http.setRequestMethod("GET");
            http.connect();

            InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            JSONParser parser = new JSONParser();

            br.close();
            in.close();

            http.disconnect();

            String duration = "";
            String distance = "";

            JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("route");

            JSONArray jsonArray = (JSONArray) jsonObject1.get("traoptimal");

            JSONObject jsonObject2 = (JSONObject) jsonArray.get(0);
            JSONObject jsonObject3 = (JSONObject) jsonObject2.get("summary");

            duration = jsonObject3.get("duration").toString();

            if (duration == null) {
                System.out.println("!!!");
                return new String[]{"", ""};
            }

            int time = Integer.parseInt(duration) / 1000; // 초

            if (time / 60 >= 60) {
                time /= 3600;
                duration = time + " 시간 " + time % 60 + " 분";
            } else {
                duration = time / 60 + " 분";
            }

            distance = Double.parseDouble(jsonObject3.get("distance").toString()) / 1000 + " km";

            return new String[]{duration, distance};
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}

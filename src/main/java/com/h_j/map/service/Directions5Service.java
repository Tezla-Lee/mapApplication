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
//            String api = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=" +
//                    departure.getLongitude() + "," + departure.getLatitude() +
//                    "&goal=" + destination.getLongitude() + "," + destination.getLatitude();

            String api = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=127.1058342,37.359708&goal=129.075986,35.179470";
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
                System.out.println(line);
                sb.append(line).append("\n");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = null;

            jsonObject = (JSONObject) parser.parse(sb.toString());
            br.close();
            in.close();

            http.disconnect();
//            System.out.println("위도 : " + y + "경도 : " + x);

            JSONArray jsonArray = (JSONArray)jsonObject.get("summary");

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

            System.out.println(duration);
            System.out.println(distance);

            return new String[] {duration, distance};
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}

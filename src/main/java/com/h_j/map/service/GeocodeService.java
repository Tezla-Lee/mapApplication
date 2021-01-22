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
import java.net.URLEncoder;

@Service
public class GeocodeService {

    public void updateGeo(LocationDto locationDto) {
        String clientId = "9bdec1tgmw";  //clientId
        String clientSecret = "UOEzYHQGVrBh7PDty5kWKTrELIebbFwyWTEYYLRP";  //clientSecret

        try {
           String addr = URLEncoder.encode("여수","utf-8");
           String api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
           StringBuffer sb = new StringBuffer();
           URL url = new URL(api);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            http.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            http.setRequestMethod("GET");
            http.connect();

            InputStreamReader in = new InputStreamReader(http.getInputStream(),"utf-8");
            BufferedReader br = new BufferedReader(in);
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject;
            JSONObject jsonObject2;
            JSONArray jsonArray;
            String x = "";
            String y = "";

            jsonObject = (JSONObject) parser.parse(sb.toString());
            jsonArray = (JSONArray)jsonObject.get("addresses");


            for(int i = 0; i<jsonArray.size(); i++) {
                jsonObject2 = (JSONObject) jsonArray.get(i);
                if(null != jsonObject2.get("x")) {
                    x = jsonObject2.get("x").toString();
                }
                if(null != jsonObject2.get("y")) {
                    y = jsonObject2.get("y").toString();
                }
            }
            br.close();
            in.close();

            http.disconnect();
            System.out.println("위도 : " + y + "경도 : " + x);

            locationDto.setLongitude(x);
            locationDto.setLatitude(y);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
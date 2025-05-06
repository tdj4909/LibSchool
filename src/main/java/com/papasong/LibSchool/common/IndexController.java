package com.papasong.LibSchool.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/libList")
    public String libList(Model model) throws IOException {

        String serviceKey = "";

        StringBuilder urlBuilder = new StringBuilder("http://api.kcisa.kr/openapi/API_CNV_065/request"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*서비스키*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*세션당 요청레코드수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지수*/
        urlBuilder.append("&" + URLEncoder.encode("schNm","UTF-8") + "=" + URLEncoder.encode("이담", "UTF-8")); /*학교명(필수값, 2자이상)*/
        urlBuilder.append("&" + URLEncoder.encode("dist","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*인접 거리*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept","application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        } else {

            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {

            sb.append(line);

        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        ApiDto dto = objectMapper.readValue(sb.toString(), ApiDto.class);

        model.addAttribute("list", dto.getResponse().getBody().getItems().getItem());

        return "libList";
    }

}

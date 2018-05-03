package com.codecool.sportSite.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    public JSONObject getJson(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonText = restTemplate.getForEntity(url, String.class).getBody();
        return new JSONObject(jsonText);
    }

    public ResponseEntity<String> postJson(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        System.out.println(entity.getBody());
        return restTemplate.postForEntity(url, entity, String.class);
    }

    public Map<String, String> getQuote(String url){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new URL(url), HashMap.class);
        } catch (IOException e){
            System.out.println("There is no quote service!");
            return new HashMap<>();
        }
    }

    public List<JSONObject> getNews(String url){
        JSONObject newsData = getJson(url);
        JSONArray news = (JSONArray) newsData.get("articles");
        JSONObject singleNews;
        List<JSONObject> newsList = new ArrayList<>();
        for (int i = 0; i < news.length(); i++){
            singleNews = news.getJSONObject(i);
            newsList.add(singleNews);
        }
        return newsList;
    }
}


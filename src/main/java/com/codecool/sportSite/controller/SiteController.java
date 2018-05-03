package com.codecool.sportSite.controller;

import com.codecool.sportSite.model.User;
import com.codecool.sportSite.service.ApiService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Controller
public class SiteController {

    @Autowired
    ApiService apiService;

    private static final String NEWS_URL = "http://sportsitenews.herokuapp.com/new-news";
    //    private static final String NEWS_URL = "http://localhost:60003/new-news";
    private static final String QUOTE_URL = "http://sportsitequotes.herokuapp.com/new-quote";
//    private static final String QUOTE_URL = "http://localhost:60002/new-quote";

    @GetMapping(value = "/")
    public String indexView(@ModelAttribute("user") User user, Model model) {
        Map<String, String> quoteData = apiService.getQuote(QUOTE_URL);
        model.addAttribute("newquote", quoteData.get("quoteText"));
        model.addAttribute("quoteauthor", quoteData.get("quoteAuthor"));
        List<JSONObject> newsList = apiService.getNews(NEWS_URL);
        model.addAttribute("articles", newsList);
        return "index";
    }
}

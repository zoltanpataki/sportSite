package com.codecool.sportSite.controller;

import com.codecool.sportSite.model.User;
import com.codecool.sportSite.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Controller
public class SiteController {

    @Autowired
    ApiService apiService;

    private static final String QUOTE_URL = "http://localhost:60002/new-quote";

    @GetMapping(value = "/")
    public String indexView(@ModelAttribute("user") User user, Model model) {
        Map<String, String> quoteData = apiService.getQuote(QUOTE_URL);
        System.out.println(quoteData.get("quoteText"));
        model.addAttribute("newquote", quoteData.get("quoteText"));
        System.out.println(quoteData.get("quoteAuthor"));
        model.addAttribute("quoteauthor", quoteData.get("quoteAuthor"));
        return "index";
    }
}

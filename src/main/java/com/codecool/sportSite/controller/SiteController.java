package com.codecool.sportSite.controller;

import com.codecool.sportSite.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class SiteController {

    @GetMapping(value = "/")
    public String indexView(@ModelAttribute("user") User user) {
        return "index";
    }
}

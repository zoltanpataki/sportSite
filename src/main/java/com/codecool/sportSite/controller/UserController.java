package com.codecool.sportSite.controller;

import com.codecool.sportSite.model.User;
import com.codecool.sportSite.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static sun.plugin2.util.PojoUtil.toJson;

@Controller
@SessionAttributes({"user"})
public class UserController {
    private static final String REGISTER_URL = "http://sportsiteuser.herokuapp.com/register";

    @Autowired
    private ApiService apiService;

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestParam Map<String, String> body, User user) throws IOException {
        System.out.println("reg started");
        ResponseEntity<String> response = apiService.postJson(REGISTER_URL, toJson(user));
        HttpStatus status = response.getStatusCode();
        String restCall = response.getBody();
        System.out.println(restCall);

        if (status == HttpStatus.OK) {
            return "Success. Body: " + restCall;
        }
        return "index";
    }
}

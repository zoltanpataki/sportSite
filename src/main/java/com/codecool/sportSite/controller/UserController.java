package com.codecool.sportSite.controller;

import com.auth0.SessionUtils;
import com.codecool.sportSite.model.User;
import com.codecool.sportSite.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static sun.plugin2.util.PojoUtil.toJson;

@Controller
@SessionAttributes({"user"})
public class UserController {
    private static final String REGISTER_URL = "http://sportsiteuser.herokuapp.com/register";
    private static final String LOGIN_URL = "http://sportsiteuser.herokuapp.com/login";
//    private static final String REGISTER_URL = "http://localhost:60001/register";
//    private static final String LOGIN_URL = "http://localhost:60001/login";

    @Autowired
    private ApiService apiService;

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @PostMapping(value="/register")
    public String registerUser(User user) throws IOException {
        ResponseEntity<String> response = apiService.postJson(REGISTER_URL, toJson(user));
        HttpStatus status = response.getStatusCode();
        String restCall = response.getBody();
        System.out.println(restCall);

        if (status == HttpStatus.OK) {
            return "index";
        }
        return "index";
    }

    @PostMapping(value = "/login")
    public String loginUser(User user) throws IOException {
        String email = user.getEmail();
        System.out.println(user.getEmail());
        String password = user.getPassword();
        System.out.println(user.getPassword());
        String result = "{" + "\"email\": " + "\"" + email + "\"" + ",\"password\": " + "\"" + password + "\"" + "}";
        System.out.println(result);

        ResponseEntity<String> response = apiService.postJson(LOGIN_URL, result);
        HttpStatus status = response.getStatusCode();
        System.out.println(status);
        String restCall = response.getBody();
        System.out.println(restCall);

        if (status == HttpStatus.OK) {
            return "index";
        }
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final HttpServletRequest req, User user) {
        String url = "https://sportsite.eu.auth0.com/userinfo";
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        apiService.getUserInfo(url, accessToken);

        if (accessToken != null) {
            model.put("userAccess", accessToken);
        } else if (idToken != null) {
            model.put("userId", idToken);
        }
        return "index";
    }
}

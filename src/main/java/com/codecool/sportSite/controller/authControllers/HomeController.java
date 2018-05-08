package com.codecool.sportSite.controller.authControllers;

import com.auth0.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final HttpServletRequest req) {
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        if (accessToken != null) {
            model.put("userId", accessToken);
        } else if (idToken != null) {
            model.put("userId", idToken);
        }
        return "index";
    }
}

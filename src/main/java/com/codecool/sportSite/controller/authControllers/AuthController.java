package com.codecool.sportSite.controller.authControllers;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    private final AuthenticationController controller;
    private final String userInfoAudience;

    @Autowired
    public AuthController(AppConfig config) {
        controller = AuthenticationController.newBuilder(config.getDomain(), config.getClientId(), config.getClientSecret())
                .build();
        userInfoAudience = String.format("https://%s/userinfo", config.getDomain());
    }

    public Tokens handle(HttpServletRequest request) throws IdentityVerificationException {
        return controller.handle(request);
    }

    public String buildAuthorizeUrl(HttpServletRequest request, String redirectUri) {
        return controller.buildAuthorizeUrl(request, redirectUri)
                .withAudience(userInfoAudience)
                .withScope("openid profile")
                .build();
    }
}

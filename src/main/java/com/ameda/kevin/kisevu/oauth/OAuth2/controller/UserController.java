package com.ameda.kevin.kisevu.oauth.OAuth2.controller;
/*
*
@author ameda
@project OAuth2
*
*/

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserController {


    @GetMapping("/user-info")
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User principal){
        return principal.getAttributes();
    }
}

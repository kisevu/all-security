package com.ameda.kevin.kisevu.oauth.OAuth2.controller;
/*
*
@author ameda
@project OAuth2
*
*/


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {

    @GetMapping("/success")
    public String success(){
        return "Successfully authenticated.";
    }

}

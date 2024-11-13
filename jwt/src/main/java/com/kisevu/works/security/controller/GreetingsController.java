package com.kisevu.works.security.controller;
/*
*
@author ameda
@project jwt
*
*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {


    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}

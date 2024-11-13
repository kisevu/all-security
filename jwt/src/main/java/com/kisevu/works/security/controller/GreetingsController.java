package com.kisevu.works.security.controller;
/*
*
@author ameda
@project jwt
*
*/

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user(){
        return "Hello User";
    }
}

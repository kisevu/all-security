package com.kisevu.works.security.controller;
/*
*
@author ameda
@project jwt
*
*/

import com.kisevu.works.security.DTO.LoginRequest;
import com.kisevu.works.security.DTO.LoginResponse;
import com.kisevu.works.security.jwt.Jwtutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class GreetingsController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private Jwtutils jwtutils;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin";
    }

    @PreAuthorize("hasAnyRole('USER','ASSISTANT')")
    @GetMapping("/user")
    public String user(){
        return "Hello User";
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request){
        Authentication authentication;
        try{
            authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            ));
        }catch (AuthenticationException ex){
            Map<String, Object> map = new HashMap<>();
            map.put("message","Bad Credentials");
            map.put("status",false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtutils.generateTokenFromUsername(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        LoginResponse loginResponse = LoginResponse.builder()
                .username(userDetails.getUsername())
                .roles(roles)
                .jwtToken(jwtToken)
                .build();
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String,Object> profile = new HashMap<>();
        profile.put("username",userDetails.getUsername());
        profile.put("roles",userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
        );
        profile.put("message","User specific info from server side");
        return ResponseEntity.ok(profile);
    }
}

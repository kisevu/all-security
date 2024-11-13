package com.ameda.kevin.kisevu.oauth.OAuth2.config;
/*
*
@author ameda
@project OAuth2
*
*/

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//                .formLogin(form ->form.defaultSuccessUrl("/success",true));
                .oauth2Login(oauth2-> oauth2.defaultSuccessUrl("http://localhost:3000/dashboard",true));
        return httpSecurity.build();

    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();  // Loads the .env file into the environment
    }

}

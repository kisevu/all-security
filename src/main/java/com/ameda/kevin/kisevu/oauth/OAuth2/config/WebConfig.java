package com.ameda.kevin.kisevu.oauth.OAuth2.config;
/*
*
@author ameda
@project OAuth2
*
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
        /*
     *
     @author ameda
     @project OAuth2
     *
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedOrigins("http://localhost:3000")
                       .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                       .allowedHeaders("*")
                       .allowCredentials(true);
            }
        };
    }
}

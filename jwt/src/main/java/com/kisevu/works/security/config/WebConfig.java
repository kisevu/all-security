package com.kisevu.works.security.config;
/*
*
@author ameda
@project jwt
*
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
       *
       @author ameda
       @project jwt
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

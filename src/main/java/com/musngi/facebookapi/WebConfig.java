package com.musngi.facebookapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                // Allow frontend dev (localhost) and deployed frontend
                .allowedOrigins(
                    "http://localhost:5173",                       // local dev
                    "https://facebook-frontend-4p6j.onrender.com"  // deployed frontend
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}

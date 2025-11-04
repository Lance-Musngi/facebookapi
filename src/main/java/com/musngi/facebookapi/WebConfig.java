package com.musngi.facebookapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for CORS.
 * Makes allowed origins configurable via the environment variable ALLOWED_ORIGINS.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String[] getAllowedOrigins() {
        String env = System.getenv("ALLOWED_ORIGINS");
        if (env != null && !env.trim().isEmpty()) {
            return env.split("\\s*,\\s*");
        }
        // Default origins for local dev and Render frontend
        return new String[]{
                "http://localhost:5173",  // Vite
                "http://localhost:3000",  // CRA
                "https://facebook-frontend-4p6j.onrender.com" // Example Render frontend URL
        };
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(getAllowedOrigins())
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}

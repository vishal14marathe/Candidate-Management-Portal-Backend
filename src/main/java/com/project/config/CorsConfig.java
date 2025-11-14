package com.project.config;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> 2c8a9bc337fb232742624c6aa4705a15a0573a1a
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
<<<<<<< HEAD
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:5173",  // Vite default port
                    "http://localhost:3000",  // React default port
                    "http://localhost:4200"   // Angular default port
                )
=======

    @Value("${frontend_url}")
    private String frontend_url;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontend_url)
>>>>>>> 2c8a9bc337fb232742624c6aa4705a15a0573a1a
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

package com.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: Auto-generated Javadoc
/**
 * The Class CorsConfig.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
    /**
     * Adds the cors mappings.
     *
     * @param registry the registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowCredentials(true)
            .maxAge(3600);
    }
}

package com.example.AdminMicroservice.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
// TODO: Auto-generated Javadoc

/**
 * This is the configuration of swagger .
 *
 * @author Pushpraj singh pawar
 */
@Configuration
public class SwaggerConfig {  
    
    /**
     * Custom open API.
     *
     * @return the open api customizer
     */
    @Bean
	public OpenApiCustomizer customOpenAPI() {
        return openApi -> {
            Info info = new Info();
            info.setTitle("Issue Tracking Software");
            info.setDescription("Our Spring boot web app allows you to post your issues to the experts that can help you resolve them");
            info.setVersion("Version 1");
            info.setContact(new Contact().email("pushpraj-singh.pawar@capgemini.com").name("Pushp raj Singh Pawar"));
            openApi.setInfo(info);
        };
    }
}

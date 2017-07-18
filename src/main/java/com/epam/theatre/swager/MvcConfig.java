package com.epam.theatre.swager;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    //For Swagger Static Resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //Swagger json
        registry.addResourceHandler("/v2/api-docs?group=cinema/**").addResourceLocations("/v2/");
        //Swagger UI
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("/swagger-ui.html");
        //Swagger Style and JavaScript for UI
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        //Swagger Images for UI
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");

    }

}

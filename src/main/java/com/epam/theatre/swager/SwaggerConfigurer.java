package com.epam.theatre.swager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.epam.theatre.rest.controller")
public class SwaggerConfigurer {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cinema")
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
      return or(
          regex("/tickets.*"),
          regex("/users.*"),
          regex("/events.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CINEMA")
                .description("Spring REST WS with Swagger")
                .version("2.0")
                .build();
    }
}

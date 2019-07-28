package com.kamalova.realm.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.zalando.logbook.Logbook;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Logbook Logbook() {
        return Logbook.create();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.kamalova.realm.api.controller"))
                .paths(path())
                .build()
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Realm Management REST API")
                .contact(new Contact("Irina Kamalova", "https://github.com/irenkamalova", "irenkamalova@gmail.com"))
                .version("1.0.0")
                .build();
    }

    private Predicate<String> path() {
        return Predicates.and(
                PathSelectors.regex("/service/user/.*"),
                Predicates.not(PathSelectors.regex("/service/user/")));
    }
}
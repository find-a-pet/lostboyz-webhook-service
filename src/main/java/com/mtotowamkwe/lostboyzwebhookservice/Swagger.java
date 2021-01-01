package com.mtotowamkwe.lostboyzwebhookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class Swagger {

    @Autowired
    private Environment environment;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/v1/webhook.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().description(description()).title(title()).version(version()).build());
    }

    protected String description() {
        return this.environment.getProperty("description");
    }

    protected String title() {
        return this.environment.getProperty("title");
    }

    protected String version() {
        return this.environment.getProperty("version");
    }
}
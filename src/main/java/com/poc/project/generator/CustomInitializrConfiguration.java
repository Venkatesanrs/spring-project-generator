package com.poc.project.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;
import io.spring.initializr.web.support.SaganInitializrMetadataUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.poc.project.generator.config")
@EnableConfigurationProperties(CustomInitializrProperties.class)
//@PropertySource("classpath:custom-application.yml")


class CustomInitializrConfiguration {

    private RestTemplate restTemplate = new RestTemplateBuilder().build();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CustomInitializrProperties customInitializrProperties;
    InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy = new SaganInitializrMetadataUpdateStrategy(restTemplate, objectMapper);

    @Bean
    InitializrMetadataProvider customInitializrMetadataProvider(InitializrProperties initializrProperties) {

        System.out.println("envv INNV"+ initializrProperties.getPackageName().getValue());

        System.out.println("envv"+ customInitializrProperties.getInitializr().getPackageName().getValue());

        //System.out.println("123344556611"+ customInitializrProperties.getTestt());
        InitializrMetadata i = InitializrMetadataBuilder
                .fromInitializrProperties(customInitializrProperties.getInitializr())
                .withInitializrProperties(initializrProperties, true)
                .build();

        System.out.println("envviiii"+ i.getDependencies().getDescription());

        return new DefaultInitializrMetadataProvider(i, initializrMetadataUpdateStrategy);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

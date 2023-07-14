package com.poc.project.generator.config;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(CustomInitializrProperties.class)

public class CustomInitializrConfiguration {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy = new SaganInitializrMetadataUpdateStrategy(restTemplate, objectMapper);

    @Autowired
    private CustomInitializrProperties customInitializrProperties;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    InitializrMetadataProvider customInitializrMetadataProvider(InitializrProperties initializrProperties) {

        InitializrMetadata i = InitializrMetadataBuilder
                .fromInitializrProperties(customInitializrProperties.getInitializr())
                .withInitializrProperties(initializrProperties, true)
                .build();

        return new DefaultInitializrMetadataProvider(i, initializrMetadataUpdateStrategy);
    }
}

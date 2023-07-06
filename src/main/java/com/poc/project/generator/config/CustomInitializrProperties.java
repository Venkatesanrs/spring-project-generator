package com.poc.project.generator.config;

import io.spring.initializr.metadata.InitializrProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
@Data
class CustomInitializrProperties {

    @NestedConfigurationProperty
    private InitializrProperties initializr = new InitializrProperties();
}
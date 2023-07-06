package com.poc.project.generator;

import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ProjectGenerationConfiguration
@ComponentScan(basePackages = "com.poc.project.generator.contributor")
//@ConditionalOnRequestedDependency("web")
public class ProjectGeneratorConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TemplateRenderer templateRenderer(Environment environment) {
        Binder binder = Binder.get(environment);
        boolean cache = binder.bind("spring.mustache.cache", Boolean.class).orElse(true);
        //https://github.com/wzlee/demo/blob/1710b4f1c182c94c32c92c9e8eccb13e948252f1/src/main/java/com/landray/demo/config/InitializrConfig.java#L92
        return new MustacheTemplateRenderer("classpath:/templates/");
    }
}

package com.poc.project.generator;

import io.spring.initializr.generator.buildsystem.BuildSystem;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.language.Language;
import io.spring.initializr.generator.packaging.Packaging;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.project.ProjectGenerator;
import io.spring.initializr.generator.version.Version;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;

import java.util.Map;

@ProjectGenerationConfiguration
@ComponentScan(basePackages = "com.poc.project.generator")
//@ConditionalOnRequestedDependency("web")
//@PropertySource("classpath:custom-application.yml")
public class ProjectGeneratorConfiguration {

    @Bean
    public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
        MustacheViewResolver mustacheViewResolver
                = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("classpath:/template/");
        //mustacheViewResolver.setSuffix(".html");
        mustacheViewResolver.setCache(false);
//        MustacheTemplateLoader mustacheTemplateLoader
//                = new MustacheTemplateLoader();
//        mustacheTemplateLoader.setResourceLoader(resourceLoader);
//        mustacheViewResolver.setTemplateLoader(mustacheTemplateLoader);
        return mustacheViewResolver;
    }


    @Bean
    @ConditionalOnMissingBean
    public TemplateRenderer templateRenderer(Environment environment) {
        Binder binder = Binder.get(environment);
        boolean cache = binder.bind("spring.mustache.cache", Boolean.class).orElse(true);
        //https://github.com/wzlee/demo/blob/1710b4f1c182c94c32c92c9e8eccb13e948252f1/src/main/java/com/landray/demo/config/InitializrConfig.java#L92
        TemplateRenderer templateRenderer = new MustacheTemplateRenderer("classpath:/templates/");
        return templateRenderer;
    }
}

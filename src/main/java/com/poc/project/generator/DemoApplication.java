package com.poc.project.generator;

import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ProjectGenerationConfiguration.class),
		@ComponentScan.Filter(type=FilterType.REGEX,
				pattern="com\\.poc\\.project\\.generator\\..*")
})
@Import({CustomInitializrConfiguration.class })
//@PropertySource("classpath:custom-application.yml")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	public TemplateRenderer templateRenderer(Environment environment) {
		Binder binder = Binder.get(environment);
		boolean cache = binder.bind("spring.mustache.cache", Boolean.class).orElse(true);
		TemplateRenderer templateRenderer = new MustacheTemplateRenderer("classpath:/templates/");
		return templateRenderer;
	}
}



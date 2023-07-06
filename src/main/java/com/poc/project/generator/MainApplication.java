package com.poc.project.generator;

import com.poc.project.generator.config.CustomInitializrConfiguration;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ProjectGenerationConfiguration.class),
		@ComponentScan.Filter(type=FilterType.REGEX,
				pattern="com\\.poc\\.project\\.generator\\..*")
})
@Import({CustomInitializrConfiguration.class })
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
package com.poc.project.generator;

import com.poc.project.generator.utils.FileUtils;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Component
public class DockerfileProjectContributor implements ProjectContributor {

    @Autowired
    ProjectDescription pd;
    @Autowired
    TemplateRenderer rr;

    @Override
    public void contribute(Path projectRoot) throws IOException {
        loadMainResources(projectRoot);
    }

    private void loadMainClasses(Path projectRoot) {

    }

    private void loadMainResources(Path projectRoot) throws IOException {
        Files.createDirectories(projectRoot.resolve("src/main/resources"));

        Map mp = Map.of("packageName", pd.getPackageName());
        String ss = rr.render("SwaggerConfig", mp);

        FileUtils.createFile(
                ss,
                Files.createFile(projectRoot.resolve("src/main/resources/Dockerfile.mustache")));
//        FileUtils.copyResource(
//                Path.of(ResourceUtils.getFile("classpath:templates/SwaggerConfig.java").toURI()),
//                Files.createFile(projectRoot.resolve("src/main/resources/SwaggerConfig.java")));

    }

    private void loadTestClasses() {

    }

    private void loadTestResources() {

    }

    private void loadRootResources() {

    }
}
package com.poc.project.generator.contributor;

import com.poc.project.generator.util.FileUtils;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Component
//@ConditionalOnBuildSystem(GradleBuildSystem.ID)
public class DockerfileProjectContributor implements ProjectContributor {

    @Override
    public void contribute(Path projectRoot) throws IOException {
        loadMainResources(projectRoot);
    }

    private void loadMainClasses(Path projectRoot) {
//target/demo-2-0.0.1-SNAPSHOT.jar
    }

    private void loadMainResources(Path projectRoot) throws IOException {
        Files.createDirectories(projectRoot.resolve(ProjectStructure.Resources.getPath()));

        FileUtils.copyResource(
                Path.of(ResourceUtils.getFile("classpath:templates/Dockerfile").toURI()),
                Files.createFile(projectRoot.resolve("Dockerfile")));

    }

    private void loadTestClasses() {

    }

    private void loadTestResources() {

    }

    private void loadRootResources() {

    }
}
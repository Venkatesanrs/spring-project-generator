package com.poc.project.generator.contributor;

import com.poc.project.generator.util.FileUtils;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@ConditionalOnRequestedDependency("web")
public class DockerfileProjectContributor implements ProjectContributor {

    @Autowired
    ProjectDescription projectDescription;

    private static final String GRADLE_TARGET = "/build/libs/";
    private static final String MAVEN_TARGET = "/target/";

    @Override
    public void contribute(Path projectRoot) throws IOException {
        loadRootResources(projectRoot);
    }

    private void loadRootResources(Path projectRoot) throws IOException {
        Files.createDirectories(projectRoot.resolve(ProjectStructure.Resources.getPath()));
        FileUtils.copyResource(
                Path.of(ResourceUtils.getFile("classpath:templates/Dockerfile").toURI()),
                Files.createFile(projectRoot.resolve("Dockerfile")));
    }

    private Path getArtifactPath() throws FileNotFoundException {
        var targetPath = GradleBuildSystem.ID.equals(projectDescription.getBuildSystem().id())?GRADLE_TARGET:MAVEN_TARGET;
        return Path.of(ResourceUtils.getFile("./"+targetPath + projectDescription.getArtifactId() + projectDescription.getVersion()+".jar").toURI());
    }
}
//package com.poc.project.generator.contributor;
//
//import com.poc.project.generator.util.FileUtils;
//import io.spring.initializr.generator.io.template.TemplateRenderer;
//import io.spring.initializr.generator.project.ProjectDescription;
//import io.spring.initializr.generator.project.contributor.ProjectContributor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Map;
//
//@Component
//public class SwaggerConfigProjectContributor implements ProjectContributor {
//
//    @Autowired
//    ProjectDescription projectDescription;
//    @Autowired
//    TemplateRenderer templateRenderer;
//
//    @Override
//    public void contribute(Path projectRoot) throws IOException {
//        loadMainResources(projectRoot);
//    }
//
//    private void loadMainClasses(Path projectRoot) {
//
//    }
//
//    private void loadMainResources(Path projectRoot) throws IOException {
//        Files.createDirectories(projectRoot.resolve("src/main/resources"));
//        Map<String, String> mp = Map.of("packageName", projectDescription.getPackageName());
//        String ss = templateRenderer.render("SwaggerConfig", mp);
//System.out.println("zxxx"+projectDescription.getBuildSystem().id());
//        FileUtils.createFile(
//                ss,
//                Files.createFile(projectRoot.resolve("src/main/resources/Dockerfile.mustache")));
////        FileUtils.copyResource(
////                Path.of(ResourceUtils.getFile("classpath:templates/SwaggerConfig.java").toURI()),
////                Files.createFile(projectRoot.resolve("src/main/resources/SwaggerConfig.java")));
//
//    }
//
//    private void loadTestClasses() {
//
//    }
//
//    private void loadTestResources() {
//
//    }
//
//    private void loadRootResources() {
//
//    }
//}
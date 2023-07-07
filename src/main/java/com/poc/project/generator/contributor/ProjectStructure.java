package com.poc.project.generator.contributor;

public enum ProjectStructure {
    Source("src/main"),
    Test("src/test"),
    Resources("src/main/resources"),
    TestResources("src/test/resources");

    private final String path;
    ProjectStructure(String path) {
    this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}

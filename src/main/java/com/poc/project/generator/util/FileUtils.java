package com.poc.project.generator.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static void copyResource(Path src, Path dest) throws IOException {
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void createFile(String content, Path dest) throws IOException {
        Files.writeString(dest, content);
    }
}

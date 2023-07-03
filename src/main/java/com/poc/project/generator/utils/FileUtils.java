package com.poc.project.generator.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static void copyResource(Path src, Path dest) throws IOException {
System.out.println("aaaaaaaaaaaaa"+ src.toString() + "   --    "+ dest.toString());
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void createFile(String content, Path dest) throws IOException {
        //System.out.println("aaaaaaaaaaaaa"+ src.toString() + "   --    "+ dest.toString());
        Files.writeString(dest, content);
    }
}

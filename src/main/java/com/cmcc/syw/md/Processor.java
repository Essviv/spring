package com.cmcc.syw.md;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 批量处理MD文件
 * <p>
 * Created by patrick on 2017/1/25.
 */
public class Processor {
    public static void main(String[] args) throws IOException {
        final String INPUT_DIR = "D:\\workspace\\blogs";
        final String OUTPUT_DIR = "C:\\Users\\patrick\\Desktop";

        Path startPath = Paths.get(OUTPUT_DIR);

        //1. copy
        FileUtils.copyDirectoryToDirectory(new File(INPUT_DIR), new File(OUTPUT_DIR));

        //2. traverse
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String prepend = prepend(file);
                if (StringUtils.isBlank(prepend)) {
                    return FileVisitResult.CONTINUE;
                }

                File oldFile = file.toFile();
                String oldContent = FileUtils.readFileToString(oldFile);
                String newContent = prepend + oldContent;

                FileUtils.writeStringToFile(oldFile, newContent, "utf-8");
                oldFile.renameTo(new File(newName(oldFile)));

                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static String newName(File file) {
        String fileName = file.getName();
        String pathName = file.getParent();

        String prepend = new SimpleDateFormat("yyyy-MM-dd-").format(new Date());
        return pathName + "\\" + prepend + fileName;
    }

    private static String prepend(Path path) {
        File file = path.toFile();

        StringBuilder stringBuilder = new StringBuilder();
        if (file.isFile() && FilenameUtils.isExtension(file.getName(), new String[]{"md", "MD"})) {
            String filename = file.getName();
            stringBuilder.append("---").append(System.lineSeparator());
            stringBuilder.append("title: ").append(filename.substring(0, filename.lastIndexOf("."))).append(System.lineSeparator());
            stringBuilder.append("author: ").append("essviv").append(System.lineSeparator());
            stringBuilder.append("date: ").append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ssZ").format(new Date())).append(System.lineSeparator());
            stringBuilder.append("---").append(System.lineSeparator());
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }


}

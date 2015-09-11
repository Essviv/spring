package com.cmcc.syw.concurrency;

import java.io.*;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class ShowAllFiles {
    private static final String OFFSET = "    ";
    private static final String NEWLINE = System.getProperty("line.separator");

    private void showDirectory(String prefix, File file, Writer writer) throws IOException {
        if (!file.exists() || !file.isDirectory() || writer == null) {
            return;
        }

        writer.write(prefix + file.getName());
        writer.write(NEWLINE);

        File[] files = file.listFiles();
        if(files==null || files.length == 0){
            return;
        }

        for (File file1 : files) {
            if (file1.isDirectory()) {
                showDirectory(prefix + OFFSET, file1, writer);
            } else {
                showFile(prefix + OFFSET, file1, writer);
            }
        }
    }

    private void showFile(String prefix, File file, Writer writer){
        if (!file.exists() || file.isDirectory() || writer == null || file.isHidden()) {
            return;
        }

        try {
            writer.write(prefix + file.getName());
            writer.write(NEWLINE);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void show(String pathName, String outputFilename) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename)));
        File file = new File(pathName);

        if(file.isDirectory()){
            showDirectory("", file, writer);
        }else{
            showFile("", file, writer);
        }

        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        final String filename = "C:\\Users\\Lenovo\\Desktop\\test.txt";
        final String pathname = "C:\\";


        try {
            new ShowAllFiles().show(pathname, filename);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Completed...");
    }
}

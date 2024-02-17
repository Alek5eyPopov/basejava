package com.urise.popovas.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        File file = new File(".");
        try {
            printFiles(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFiles(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                printFiles(fileList[i]);
            }
        } else {
            System.out.println(file.getCanonicalPath());
        }
    }


}

package com.kdpark.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtil {
    public static Scanner getFileReadScanner(String filePath) {
        if (filePath.charAt(0) == '/') filePath = filePath.substring(1);
        String localDir = System.getProperty("user.dir") + "/src/com/kdpark/";
        String fullPath = localDir + filePath;

        Scanner sc = null;
        try {
            File file = new File(fullPath);
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sc;
    }
}

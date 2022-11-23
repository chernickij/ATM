package com.senla.atm.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWriter {
    public static void writeToFile(String value, String fileName) {
        File file = new File("src/main/resources/" + fileName);
        try{
            OutputStream os = new FileOutputStream(file.getAbsoluteFile(), false);
            os.write(value.getBytes(), 0, value.length());
            os.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File by path " + file.getPath() + " not found.");
        } catch (IOException exception) {
            System.out.println("Problem reading file: " + exception.getMessage());
        }
    }
}
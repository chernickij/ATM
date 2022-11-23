package com.senla.atm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

public class fileWriter {
    public static void writeToFile(String line, String fileName) {
        try{
            File file = new File("src/main/resources/" + fileName);
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);

            OutputStream os = new FileOutputStream(file, false);
            os.write(line.getBytes(), 0, line.length());
            os.close();

            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}

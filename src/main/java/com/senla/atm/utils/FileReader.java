package com.senla.atm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        ClassLoader classLoader = FileReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File with name " + fileName + " cannot be found!");
        }

        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File with name" + fileName + " cannot be read: " + e.getMessage());
        }
        return lines;
    }
    public static Double readAtmBalance(String fileName){
        double AtmBalance = 0;
        ClassLoader classLoader = FileReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File with name " + fileName + " cannot be found!");
        }

        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            AtmBalance = Double.parseDouble(reader.readLine());
        } catch (IOException e){
            System.out.println("File with name" + fileName + " cannot be read: " + e.getMessage());
        }

        return AtmBalance;
    }
}

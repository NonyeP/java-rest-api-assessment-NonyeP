package com.cbfacademy.apiassessment.exceptionHandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * This class reads files
 * 
 */
public class FileHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public <T> List<T> readFromFile(Class<T> valueType) {
        try {
            File file = new File(filePath);
            return objectMapper.readValue(file, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it based on your requirement
            return null;
        }
    }

    public <T> void writeToFile(List<T> data) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it based on your requirement
        }
    }

     public static void main(String[] args) {
        //Read from file
    String filePath = "java-rest-api-assessment-NonyeP\\BudgetTrackerREADME.md";

        try (FileInputStream file = new FileInputStream(filePath);
             InputStreamReader streamReader = new InputStreamReader(file);
             LineNumberReader lineReader = new LineNumberReader(streamReader)) {
            String outputline = lineReader.readLine();
            while (outputline != null) {
                System.out.printf("%d: %s%n", lineReader.getLineNumber(), outputline);
                outputline = lineReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("FileReading Exception: " + e.getMessage());
        }
        

        //write to a file
        String data = "java-rest-api-assessment-NonyeP\\BudgetTrackerREADME.md";
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        // try (FileInputStream in = new FileInputStream(filePath);
        //     FileOutputStream out = new FileOutputStream(data);
        try{
            inputStream = new BufferedReader(new FileReader(filePath));
            outputStream = new PrintWriter(new FileWriter(
                    data));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
            System.out.println("File copied");
            inputStream.close();
            outputStream.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    } 

    
}

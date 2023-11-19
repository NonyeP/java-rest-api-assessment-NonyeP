package com.cbfacademy.apiassessment.exceptionHandlers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * This class reads json files
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

    
}

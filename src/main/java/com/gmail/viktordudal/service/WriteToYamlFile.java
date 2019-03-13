package com.gmail.viktordudal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gmail.viktordudal.model.Person;

import java.io.File;
import java.io.IOException;

public class WriteToYamlFile {

    public static void writeToYamlFile(String outputPath, Person user){

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File(outputPath), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

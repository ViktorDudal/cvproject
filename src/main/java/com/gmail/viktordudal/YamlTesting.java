package com.gmail.viktordudal;

import com.gmail.viktordudal.model.Person;

import java.io.*;
import java.util.logging.Logger;

import static com.gmail.viktordudal.service.ParseFile.parseFile;
import static com.gmail.viktordudal.service.WriteToYamlFile.writeToYamlFile;

public class YamlTesting {

    private static  final Logger LOGGER = Logger.getLogger(YamlTesting.class.getName());

    public static void main(String[] args) throws IOException {

        String inputFile = "src/main/resources/user.xml";

        Person user = parseFile(inputFile);

        String outputFile = "src/main/resources/output.yml";

        writeToYamlFile(outputFile, user);

    }
}
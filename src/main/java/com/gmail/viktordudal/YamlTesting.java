package com.gmail.viktordudal;

import com.gmail.viktordudal.model.Person;

import java.io.*;
import java.util.logging.Logger;

import static com.gmail.viktordudal.service.ParseFile.fileExtension;
import static com.gmail.viktordudal.service.ParseFile.parseFile;
import static com.gmail.viktordudal.service.ReadInput.readFromFile;
import static com.gmail.viktordudal.service.WriteToYamlFile.writeToYamlFile;

public class YamlTesting {

    public static void main(String[] args) throws IOException {

        Logger LOGGER = Logger.getLogger("vdud");

        String inputFile = "src/main/resources/user.json";

        StringBuilder builder = readFromFile(inputFile);

        char ex = fileExtension(builder);

        Person user = parseFile(ex, inputFile);

        String outputFile = "src/main/resources/output.yml";

        writeToYamlFile(outputFile, user);

    }
}
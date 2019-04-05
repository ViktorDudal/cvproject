package com.gmail.viktordudal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gmail.viktordudal.model.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseFile {

    private static ObjectMapper mapper;
    private static XmlMapper xmlMapper;
    private static final Logger LOGGER = Logger.getLogger(ParseFile.class.getName());

    public static Person parseFile(String file) throws IOException {

        Person user = null;
        mapper = new ObjectMapper(new YAMLFactory());
        xmlMapper = new XmlMapper();

        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()){
                char firstChar = line.trim().charAt(0);
                try {
                    if (firstChar == '{' || firstChar == '-') {
                        user = mapper.readValue(new File(file), Person.class);
                    }
                    if (firstChar == '<') {
                        user = xmlMapper.readValue(new File(file), Person.class);
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IOException: ", e);
                }
            }
        }
        return user;
    }
}
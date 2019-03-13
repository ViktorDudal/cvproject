package com.gmail.viktordudal.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {

    public static StringBuilder readFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        builder.append(reader.readLine());
        reader.close();
        return builder;
    }
}

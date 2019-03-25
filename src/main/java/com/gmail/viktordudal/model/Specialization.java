package com.gmail.viktordudal.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Specialization{

    JAVA("Java"),
    PYTHON("Python"),
    DEV_OPS("DevOps"),
    WEB_UI("WebUI"),
    RUBY("Ruby"),
    ATQC("ATQC");

    private static final Map<String, Specialization> VALUES = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(value -> VALUES.put(value.getName(), value));
    }

    private String name;

    Specialization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Specialization getByName(String name) {
        return VALUES.get(name);
    }
}

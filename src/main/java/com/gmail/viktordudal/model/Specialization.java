package com.gmail.viktordudal.model;

public enum Specialization{

    JAVA("Java"),
    PYTHON("Python"),
    DEV_OPS("DevOps"),
    WEB_UI("WebUI"),
    RUBY("Ruby");

    private String name;

    Specialization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

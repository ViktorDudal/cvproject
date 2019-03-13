package com.gmail.viktordudal.service;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

import static org.testng.Assert.*;

public class ReadInputTest {



    @Test
    public void testReadFromFile() throws IOException {
        ReadInput readInput = new ReadInput();
        StringBuilder builder = new StringBuilder();
        String file = "src/test/resources/test.txt";
//        Assert.assertEquals(builder.append("surname"), readInput.readFromFile(file));
//        Assert.assertSame(builder.append("surname"), readInput.readFromFile(file));
        assertEquals(builder, readInput.readFromFile(file));
    }
}
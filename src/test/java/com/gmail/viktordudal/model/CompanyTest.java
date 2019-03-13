package com.gmail.viktordudal.model;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.*;

public class CompanyTest {

    Company companyTest = new Company();

    @Test
    public void testGetCompanyName() {
    }

    @Test
    public void testSetCompanyName() {
        companyTest.setCompanyName("SoftServe");
        assertEquals(companyTest.getCompanyName(),"SoftServe");
    }

    @Test
    public void testGetPosition() {
    }

    @Test
    public void testSetPosition() {
        companyTest.setPosition("Engineer");
        assertEquals(companyTest.getPosition(),"Engineer");
    }

    @Test
    public void testGetWorkedFrom() {
    }

    @Test
    public void testSetWorkedFrom() {
        companyTest.setWorkedFrom(LocalDate.parse("01-01-1999", DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        assertEquals(companyTest.getWorkedFrom(),"01-01-1999");
    }

    @Test
    public void testGetWorkedTill() {
    }

    @Test
    public void testSetWorkedTill() {
        companyTest.setWorkedTill(LocalDate.parse("01-01-1999", DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        assertEquals(companyTest.getWorkedTill(),"01-01-1999");
    }
}
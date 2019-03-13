package com.gmail.viktordudal.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ContactTest {

    Contact contactTest = new Contact();


    @Test
    public void testGetPhoneNumber() {
        contactTest.setPhoneNumber(3809901234567L);
        assertEquals(contactTest.getPhoneNumber(),3809901234567L);
    }

    @Test
    public void testSetPhoneNumber() {
    }

    @Test
    public void testGetEmail() {
        contactTest.setEmail("qwerty@fdsg.com");
        assertEquals(contactTest.getEmail(),"qwerty@fdsg.com");
    }

    @Test
    public void testSetEmail() {
    }
}
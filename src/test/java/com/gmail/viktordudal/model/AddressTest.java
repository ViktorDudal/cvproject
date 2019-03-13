package com.gmail.viktordudal.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddressTest {

    Address addressTest = new Address();

    @Test
    public void testGetCity() {
    }

    @Test
    public void testSetCity() {
        addressTest.setCity("Lviv");
        assertEquals(addressTest.getCity(),"Lviv");
    }

    @Test
    public void testGetStreet() {
    }

    @Test
    public void testSetStreet() {
        addressTest.setStreet("Holovna");
        assertEquals(addressTest.getStreet(),"Holovna");
    }

    @Test
    public void testGetHouse() {
    }

    @Test
    public void testSetHouse() {
        addressTest.setHouse(18);
        assertEquals(addressTest.getHouse(),18);
    }

    @Test
    public void testGetApartment() {
    }

    @Test
    public void testSetApartment() {
        addressTest.setApartment(45);
        assertEquals(addressTest.getApartment(),45);
    }

    @Test
    public void testGetZip() {
    }

    @Test
    public void testSetZip() {
        addressTest.setZip(000000);
        assertEquals(addressTest.getZip(),000000);
    }
}
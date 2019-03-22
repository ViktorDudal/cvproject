package com.gmail.viktordudal.model;

import com.gmail.viktordudal.service.ValidatorModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Contact {

    @Size(min = 2, max = 25, message = "Field 'city' must be between 2 and 25 characters")
    private String city;

    @Size(min = 2, max = 25, message = "Field 'address' must be between 2 and 25 characters")
    private String address;

    @Size(min = 10, max = 12, message = "Field 'phoneNumber' must be between 10 and 12 characters")
    private String phoneNumber;

    @Size(min = 5, max = 45, message = "Field 'email' must be between 5 and 45 characters")
    @Email(message = "Wrong email address")
    private String email;

    public static ContactBuilder builder(){
        return new ContactBuilder();
    }

    private Contact(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public static class ContactBuilder {
        private Contact newContact;

        private ContactBuilder() {
            newContact = new Contact();
        }

        public ContactBuilder city(String city) {
            newContact.city = city;
        return this;
        }


        public ContactBuilder address(String address) {
            newContact.address = address;
            return this;
        }

        public ContactBuilder phoneNumber(String phoneNumber) {
            newContact.phoneNumber = phoneNumber;
        return this;
        }


        public ContactBuilder email(String email) {
            newContact.email = email;
            return this;
        }

        public Contact build(){
            new ValidatorModel().validate(newContact);
            return newContact;
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

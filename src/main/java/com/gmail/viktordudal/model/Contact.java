package com.gmail.viktordudal.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Contact {

    @NotNull(message = "Field 'city' cannot be null")
    @Size(min = 2, max = 25, message = "Field 'city' must be between 2 and 15 characters")
    private String city;

    @NotNull(message = "Field 'address' cannot be null")
    @Size(min = 2, max = 25, message = "Field 'address' must be between 2 and 35 characters")
    private String address;

    @NotNull(message = "Field 'phoneNumber' cannot be null")
    @Size(min = 10, max = 12, message = "Field 'phoneNumber' must be between 10 and 12 characters")
    private String phoneNumber;

    @NotNull(message = "Field 'city' cannot be null")
    @Email(message = "Wrong email address")
    private String email;

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

   /* public static class Builder {
        private Contact newContact;

        public Builder() {
            newContact = new Contact();
        }

        public Builder withCity(String city) {
            newContact.city = city;
        return this;
        }


        public Builder withAddress(String address) {
            newContact.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            newContact.phoneNumber = phoneNumber;
        return this;
        }


        public Builder withEmail(String email) {
            newContact.email = email;
            return this;
        }

        public Contact build(){
            return newContact;
        }
    }*/
}

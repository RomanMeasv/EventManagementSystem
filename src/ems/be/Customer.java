package ems.be;

import java.util.ArrayList;

public class Customer{
    private String name;
    private String email;
    private String phoneNumber;
    private String notes;

    public Customer(String name, String email, String phoneNumber, String notes) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}

package ems.be;

import java.util.ArrayList;

public class Customer{
    private int id;
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

    public Customer(int id, String name, String email, String phoneNumber, String notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }


    public int getId() {
        return id;
    }
    public void setId (int id){this.id = id;}

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


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

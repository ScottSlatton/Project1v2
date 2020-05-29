package com.banking.models;

public class Employee extends User {
    String id;

    public Employee() {
    }


    public Employee(String firstName, String lastName, String email, String password, String id) {
        super(firstName, lastName, email, password);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

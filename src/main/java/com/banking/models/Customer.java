package com.banking.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    public String id;
    private List<Account> accounts = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String email, String password, String id, List<Account> accounts) {
        super(firstName, lastName, email, password);
        this.id = id;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", accounts=" + accounts +
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

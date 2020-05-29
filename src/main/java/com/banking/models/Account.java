package com.banking.models;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.*;

public class Account {

    private String id;
    private double balance;
    private String accountType;
    private String status;
    private List<Customer> customers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();


    public Account() {
    }

    public Account(String id, double balance, String accountType, String status, List<Customer> customers, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status;
        this.customers = customers;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                ", customers=" + customers +
                ", transactions=" + transactions +
                '}';
    }

    public String getId() {
        return id;
    }

    public Account getAccountById(String id){
        if (this.getId().equals(id)){
            return this;
        }
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}


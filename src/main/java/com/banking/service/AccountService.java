package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Transaction;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account) throws BusinessException;
    Account getAccount(Account account) throws BusinessException;
    void updateBalance(Account account, Transaction transaction) throws BusinessException;
    void deleteAccount(Account account) throws BusinessException;
    void withdraw(Account account, Transaction transaction) throws BusinessException;
    void deposit(Account account, Transaction transaction) throws BusinessException;

    List<Account> getAccountsByUser(Customer customer) throws BusinessException;

}

package com.banking.dao;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

import java.util.List;

public interface AccountDao {

    void createAccount(Account account) throws BusinessException;
    Account getAccountById(String accountId) throws BusinessException;
    List<Account> getAccountsByUser(Customer customer) throws BusinessException;
    void updateAccount(Account account) throws BusinessException;
    List<Account> getAllPendingAccounts() throws BusinessException;
}

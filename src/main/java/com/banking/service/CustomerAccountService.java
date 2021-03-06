package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

public interface CustomerAccountService {

    void createCustomerAccount(Customer customer, Account account) throws BusinessException;
    void createCustomerAccount(Customer customer) throws BusinessException;
    void deleteCustomerAccount(Customer customer, Account account) throws BusinessException;
}

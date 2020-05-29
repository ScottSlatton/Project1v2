package com.banking.service.impl;

import com.banking.dao.CustomerAccountDao;
import com.banking.dao.impl.CustomerAccountDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.service.CustomerAccountService;

public class CustomerAccountServiceImpl implements CustomerAccountService {

    CustomerAccountDao dao = new CustomerAccountDaoImpl();


    @Override
    public void createCustomerAccount(Customer customer, Account account) throws BusinessException {
        try{
            dao.createCustomerAccount(customer, account);
        } catch(BusinessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createCustomerAccount(Customer customer) throws BusinessException {
        try{
            dao.createCustomerAccount(customer);
        } catch(BusinessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerAccount(Customer customer, Account account) throws BusinessException {

    }
}

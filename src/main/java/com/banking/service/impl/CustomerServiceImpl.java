package com.banking.service.impl;

import com.banking.dao.AccountDao;
import com.banking.dao.CustomerDao;
import com.banking.dao.impl.AccountDaoImpl;
import com.banking.dao.impl.CustomerDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Transaction;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import com.banking.service.TransactionService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao dao = new CustomerDaoImpl();
    final static Logger loggy = Logger.getLogger(String.valueOf(CustomerServiceImpl.class));


    @Override
    public Customer createCustomer(Customer customer) throws BusinessException{

        try{
            validateCustomer(customer);
            // Call the DAO
            dao.createCustomer(customer);
        } catch(BusinessException e){
            throw new BusinessException("Customer could not be created.");
        }
        return customer;
    }

    @Override
    public Customer customerLogin(Customer customer) throws BusinessException {

        // this is a basic Get customer from Database
        try{
//          validateCustomer(customer);

            //Get customer from DB
            customer = dao.getCustomerByLogin(customer);

            System.out.println("email: " + customer.getEmail());

            //Get Accounts from DB
            AccountService aService = new AccountServiceImpl();
            List<Account> accounts = aService.getAccountsByUser(customer);
            System.out.println("attaching accounts to customer in log in service" + accounts);

            //attach accounts to customer
            customer.setAccounts(accounts);
            System.out.println(customer.getAccounts());

            //Get Transactions from DB related to accounts
            TransactionService tService = new TransactionServiceImpl();
            tService.getTransactions(accounts);

            return customer;

        }catch(BusinessException e) {
            loggy.info(e.getMessage());
        }
        throw new BusinessException("Customer login didn't work.");
    }



    public void validateCustomer(Customer customer) throws BusinessException {
        if(customer == null){
            throw new BusinessException("Customer Object was not found.");
        } else if (!isValidName(customer.getFirstName()) && !isValidName(customer.getLastName()) ){
            throw new BusinessException("Name is invalid.");
        } else if (!isValidPassword(customer.getPassword())) {
            throw new BusinessException("Password is invalid. Password must be between 4 - 20 characters, can contain numbers.");
        }
    }


    public boolean isValidName(String name){
        boolean b = false;
        if (name.matches("[a-zA-Z0-9]{2,16}")){
            b = true;
        }
        return b;
    }
    public boolean isValidId(String id){
        boolean b = false;
        if (id.matches("MBU[A-Z]{3}[0-9]{4}")){
            b = true;
        }
        return b;
    }

    public boolean isValidPassword(String password){
        boolean b = false;
        if (password.matches("[a-zA-Z0-9]{4,20}")){
            b = true;
        }
        return b;
    }
//    private boolean isValidPhone(long phone){
//        boolean b = false;
//        if ((phone + "").matches("[0-9]{10}")){
//            b = true;
//        }
//        return b;
//    }
//    private boolean isValidCity(String city){
//        boolean b = false;
//        if (city.matches("[a-zA-Z]{2,11}")){
//            b = true;
//        }
//        return b;
//    }
    public boolean isValidEmail(String email){
        boolean b = false;
        if (email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
            b = true;
        }
        return b;
    }



    @Override
    public Customer getCustomerById(String id) throws BusinessException {
        try{
            Customer customer = dao.getCustomerById(id);
            return customer;
        } catch (BusinessException e) {
            loggy.warning(e.getMessage());
        }
        return null;
    }

//    @Override
//    public Customer getCustomerByUsername(String username) throws BusinessException {
//        try{
//            Customer customer = dao.getCustomerByUsername(username);
//            return customer;
//        } catch (BusinessException e) {
//            loggy.warning(e.getMessage());
//        }
//        return null;
//    }


    @Override
    public Customer updateCustomerPassword(String id, String Password) throws BusinessException {
        return null;
    }

    @Override
    public void deleteCustomer(String id) throws BusinessException {

    }

    @Override
    public List<Customer> getAllCustomers() throws BusinessException {
        try{
            List<Customer> accountList = dao.getAllCustomers();
            return accountList;
        } catch (BusinessException e) {
            loggy.warning(e.getMessage());
        }
        return null;
    }

}

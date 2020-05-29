package com.banking.service.impl;

import com.banking.dao.AccountDao;
import com.banking.dao.impl.AccountDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Transaction;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;

import java.util.List;
import java.util.logging.Logger;


public class AccountServiceImpl implements AccountService {


    private AccountDao dao = new AccountDaoImpl();
    final static Logger loggy = Logger.getLogger(String.valueOf(AccountServiceImpl.class));

    @Override
    public Account createAccount(Account account) throws BusinessException {
        return null;
    }

    @Override
    public Account getAccount(Account account) throws BusinessException {

        try{
            return dao.getAccountById(account.getId());
        } catch (BusinessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBalance(Account account, Transaction transaction) throws BusinessException {
        try{
            validatesAccount(account);
            //may need to validate balance input here
            // you have to setBalance before sending the account to be updated
            dao.updateAccount(account);
            //create a transaction object in database
            TransactionService tService = new TransactionServiceImpl();
            tService.createTransaction(transaction);

        } catch(BusinessException e) {
            e.printStackTrace();
            loggy.warning(e.getMessage());
        }
    }

    public void withdraw(Account account, Transaction transaction) throws BusinessException{
        double balance = account.getBalance();
        double amount = transaction.getAmount();
        double withdrawAmount = (-1 * amount);

        System.out.println("withdraw amount flipped" + withdrawAmount);

        if((withdrawAmount <= 0) && amount <= balance){
            System.out.println("passed the withdraw amount if");
            try{
                System.out.println(balance - amount);
                account.setBalance(balance - amount);
                updateBalance(account, transaction);
                System.out.println("successfully updated balance");

            } catch(BusinessException e) {
                e.printStackTrace();
            }
//            throw new BusinessException("Unable to withdraw funds.");
        } else {
            System.out.println("Invalid amount entered.");

        }
    }

    public void deposit(Account account, Transaction transaction) throws BusinessException{

        double balance = account.getBalance();
        double amount = transaction.getAmount();
        System.out.println("inside deposit");

        if ((amount >= 0)){
            try{
                account.setBalance(balance + amount);
                updateBalance(account, transaction);
                System.out.println("successfully updated balance");

            } catch(BusinessException e){
                e.printStackTrace();
            }
            throw new BusinessException("Invalid amount entered.");
        }
    }

    @Override
    public void updateAccount(Account account) throws BusinessException {
        try{
            dao.updateAccount(account);
            System.out.println("Account successfully updated.");
        }catch (BusinessException e){
            e.printStackTrace();
        }
    }

    public void transfer(Account sender, Account receiver, Transaction transaction) throws BusinessException{
        // withdraw from sender
        try {
            withdraw(sender, transaction);
            // deposit into receiver
            deposit(receiver, transaction);
        } catch(BusinessException e){
            e.printStackTrace();
        }
        throw new BusinessException("Transfer could not be completed.");
    }

    @Override
    public void deleteAccount(Account account) throws BusinessException {

    }

    @Override
    public List<Account> getAccountsByUser(Customer customer) throws BusinessException {
        try{

            System.out.println("customer: " + customer);
            return dao.getAccountsByUser(customer);

        } catch(BusinessException e){
            loggy.warning(e.getMessage());
        }
        throw new BusinessException("Could not find accounts for user.");
    }

    @Override
    public List<Account> getAllPendingAccounts() throws BusinessException {
        try{
            List<Account> accounts = dao.getAllPendingAccounts();
            return accounts;
        } catch (BusinessException e){
            e.printStackTrace();
        }
        return null;
    }

    public void validatesAccount(Account account) throws BusinessException {
        if(account == null){
            throw new BusinessException("Account was not found.");
        } else if (!isValidId(account.getId())){
            throw new BusinessException("AccountID is invalid.");
        }
    }

    private boolean isValidId(String id){
        boolean b = false;
        if (id.matches("MBA[0-9]{4}")){
            b = true;
        }
        return b;
    }
    private boolean isValidDeposit(double deposit){
        boolean b = false;
        if (deposit > 0){
            b = true;
        }
        return b;
    }
    private boolean isValidWithdrawal(double withdrawal, double balance){
        boolean b = false;
        if (withdrawal <= balance ){
            b = true;
        }
        return b;
    }

}

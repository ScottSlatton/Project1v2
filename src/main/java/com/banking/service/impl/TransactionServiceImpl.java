package com.banking.service.impl;

import com.banking.dao.TransactionDao;
import com.banking.dao.impl.TransactionDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Transaction;
import com.banking.service.TransactionService;

import java.util.List;
import java.util.logging.Logger;


public class TransactionServiceImpl implements TransactionService {

    final static Logger loggy = Logger.getLogger(String.valueOf(TransactionServiceImpl.class));

    private TransactionDao dao = new TransactionDaoImpl();
    @Override
    public void createTransaction(Transaction transaction) {
        try{
            isValidTransaction(transaction);
            // Call the DAO
            dao.createTransaction(transaction);
        } catch(BusinessException e){
            loggy.warning(e.getMessage());
        }
    }

    private Boolean isValidTransaction(Transaction transaction) throws BusinessException {
        boolean b = false;
        if(transaction.getSender() == null){
            throw new BusinessException("Invalid Transaction. Sender not found.");
        } else if (transaction.getReceiver() == null) {
            throw new BusinessException("Invalid Transaction. Receiver not found.");
        } else {
            b = true;
        }
        return b;
    }

    @Override
    public List<Transaction> getAllTransactions() throws BusinessException{
        try{
            List<Transaction> transactionList = dao.getAllTransactions();
            return transactionList;
        } catch (BusinessException e) {
            loggy.warning(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {

    }

    @Override
    public void getTransactions(List<Account> accounts) throws BusinessException {
        try{
            dao.getTransactions(accounts);
        } catch(BusinessException e){
            throw new BusinessException(e.getMessage());
        }

    }
}

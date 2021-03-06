package com.banking.dao.impl;

import com.banking.dao.TransactionDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TransactionDaoImpl implements TransactionDao {
    @Override
    public void createTransaction(Transaction transaction) throws BusinessException {

        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "{call CREATETRANSACTION(?, ?, ?, ?, ?)}";

            //fill in the ?'s

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(2, transaction.getSender().getId());
            callableStatement.setString(3, transaction.getReceiver().getId());
            callableStatement.setDouble(4, transaction.getAmount());
            //register id and Timestamp because it is an OUT param
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.TIMESTAMP);

            callableStatement.execute();

            //callableStatement should have executed and now contains the ID param

//           customer.setId(callableStatement.getString(1));
            System.out.println("Transaction Successfully Created");

        } catch (SQLException | ClassNotFoundException e) {
            throw new BusinessException("Internal error. Please don't panic.");
        }
    }

    @Override
    public Transaction getTransaction(String id) throws BusinessException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() throws BusinessException {
        List<Transaction> transactionList = new ArrayList<>();
        try(Connection connection = OracleConnection.getConnection()){
            String sql = "Select * from transaction";
            PreparedStatement ps = connection.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Transaction t = new Transaction();
                Account sender = new Account();
                Account receiver = new Account();

                sender.setId(rs.getString("senderId"));
                receiver.setId(rs.getString("receiverId"));
                t.setId(rs.getString("id"));
                t.setSender(sender);
                t.setReceiver(receiver);
                t.setAmount(rs.getDouble("amount"));

                transactionList.add(t);
            }
            return transactionList;
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        return transactionList;
    }

    @Override
    public void getTransactions(List<Account> accounts) throws BusinessException {

        //TODO Ripe for Refactoring. Should return a list of transactions.



        try(Connection connection = OracleConnection.getConnection()){

            for (Account account: accounts ) {
                String sql = "SELECT * FROM transaction WHERE receiverid = ? OR senderid = ? ORDER BY timestamp DESC";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, account.getId());
                ps.setString(2, account.getId());

                ResultSet rs = ps.executeQuery();

                List<Transaction> transactionList = new ArrayList<>();

                while(rs.next()) {
                    Transaction t = new Transaction();
                    Account sender = new Account();
                    Account receiver = new Account();

                    sender.setId(rs.getString("senderId"));
                    receiver.setId(rs.getString("receiverId"));
                    t.setId(rs.getString("id"));
                    t.setSender(sender);
                    t.setReceiver(receiver);
                    t.setAmount(rs.getDouble("amount"));
                    t.setTimestamp(rs.getTimestamp("timestamp"));

                    System.out.println("Transaction back from the DB: "+ t);

                    transactionList.add(t);
                    account.setTransactions(transactionList);
                }
            }
        } catch(ClassNotFoundException | SQLException e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(Transaction transaction) throws BusinessException {

    }


}



//    @Override
//        public List<Transaction> getAllTransactions() throws BusinessException {
//            List<Transaction> transactionList = new ArrayList<>();
//            try (Connection connection = OracleConnection.getConnection()) {
//                String sql = "SELECT id, senderid, receiverid, amount FROM TRANSACTION";
//                PreparedStatement ps = connection.prepareStatement(sql);
//                ResultSet resultSet = ps.executeQuery();
//
//                while(resultSet.next()) {
//                    Transaction t = new Transaction();
//                    Account sender = new Account();
//                    Account receiver = new Account();
//
//                    sender.setId(resultSet.getString("sender"));
//                    receiver.setId(resultSet.getString("receiver"));
//                    t.setId(resultSet.getString("id"));
//                    t.setSender(sender);
//                    t.setReceiver(receiver);
//                    t.setAmount(resultSet.getDouble("amount"));
//
//                    transactionList.add(t);
//                }
//                return transactionList;
//            } catch (ClassNotFoundException | SQLException e) {
//                throw new BusinessException("Internal Error, please don't panic.");
//            }
//    }
//}

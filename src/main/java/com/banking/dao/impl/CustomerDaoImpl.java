package com.banking.dao.impl;

import com.banking.dao.CustomerDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void createCustomer(Customer customer) throws BusinessException {

        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "{call CREATECUSTOMER(?, ?, ?, ?, ?)}";


            //fill in the ?'s

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(2, customer.getEmail());
            callableStatement.setString(3, customer.getPassword());
            callableStatement.setString(4, customer.getFirstName());
            callableStatement.setString(5, customer.getLastName());


//            List<Account> accounts = customer.getAccounts();
//            callableStatement.setDouble(3, accounts.get(0).getBalance());
//            callableStatement.setString(4, accounts.get(0).getType());
            //register id because it is an OUT param
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            //callableStatement should have executed and now contains the ID param

//           customer.setId(callableStatement.getString(1));


        } catch (SQLException | ClassNotFoundException e) {
            throw new BusinessException("Internal error. Please don't panic.");
        }
        ;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws BusinessException {
        return null;
    }

//    @Override
//    public Customer getCustomerByUsername(String Username) throws BusinessException {
//        Customer c = null;
//
//        try(Connection connection=OracleConnection.getConnection()){
//            String sql="Select customer.id AS userID, customer.username, account.balance, " +
//                    "account.id AS accountID " +
//                    "FROM Customer " +
//                    "JOIN Account ON Account.id = customer.accountid " +
//                    "WHERE username = ?";
//
//            PreparedStatement ps=connection.prepareStatement(sql);
//            ps.setString(1, Username);
//
//            ResultSet resultSet=ps.executeQuery();
//
//            List<Account> accounts = new ArrayList<>();
//            Account a = null;
//            if(resultSet.next()) {
//                c = new Customer();
//                a = new Account();
//                c.setId(resultSet.getString("userID"));
//                c.setUsername(resultSet.getString("username"));
//                a.setId(resultSet.getString("accountID"));
//                a.setBalance(resultSet.getDouble("balance"));
//                accounts.add(a);
//                c.setAccounts(accounts);
//                return c;
//            }else {
//                throw new BusinessException(Username +" does not exist");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new BusinessException("Internal Error, please don't panic.");
//
//        }
//    }

    @Override
    public Customer getCustomerByLogin(Customer customer) throws BusinessException {

        Customer c = null;

        try (Connection connection = OracleConnection.getConnection()) {

            String sql = "Select id, email, firstname, lastname FROM CUSTOMER where email = ? AND password = ?";


            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());

// --------------------   HARDCODE FOR TESTING ---------------------------
//            ps.setString(1, "example@email.com");
//            ps.setString(2, "hunter2");

            ResultSet resultSet = ps.executeQuery();

//            List<Account> accounts = new ArrayList<>();
//            Account a = null;
            if (resultSet.next()) {
                c = new Customer();
                c.setId(resultSet.getString("id"));
                c.setEmail(resultSet.getString("email"));
                c.setFirstName(resultSet.getString("firstname"));
                c.setLastName(resultSet.getString("lastname"));
                return c;

            } else {
                throw new BusinessException("Customer" + customer.getEmail() + " does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void deleteCustomer(String Username) throws BusinessException {

    }

    @Override
    public List<Customer> getAllCustomers() throws BusinessException {
        List<Customer> accountList = new ArrayList<>();
        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "Select customer.id AS userID, customer.email, account.balance, " +
                    "account.id AS accountID " +
                    "FROM Customer " +
                    "JOIN Account ON Account.id = customer.accountid";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer c = new Customer();
                Account a = new Account();
                List<Account> accounts = new ArrayList<>();

                c.setId(resultSet.getString("userID"));
                c.setEmail(resultSet.getString("email"));
                a.setId(resultSet.getString("accountID"));
                a.setBalance(resultSet.getDouble("balance"));
                accounts.add(a);
                c.setAccounts(accounts);
                accountList.add(c);
            }
            return accountList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error contact SYSADMIN");
        }
    }

    @Override
    public Customer getCustomerById(String id) throws BusinessException {
        Customer c = null;
        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "Select customer.id AS userID, customer.email" +
                    "FROM Customer " +
                    "WHERE customer.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

//            List<Account> accounts = new ArrayList<>();
//            Account account = new Account();

            if (resultSet.next()) {

                //Set user data
                c = new Customer();
                c.setId(resultSet.getString("userID"));
                c.setEmail(resultSet.getString("email"));

                //Set account data
//                account.setId(resultSet.getString("accountID"));
//                account.setBalance(resultSet.getDouble("balance"));
//
//                accounts.add(account);
//                c.setAccounts(accounts);

                //Set transaction data


                return c;
            } else {
                throw new BusinessException("User " + id + " does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");
        }
    }
}

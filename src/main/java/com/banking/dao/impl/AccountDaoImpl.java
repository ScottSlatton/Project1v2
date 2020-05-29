package com.banking.dao.impl;

import com.banking.dao.AccountDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountDaoImpl implements AccountDao {

    public void createAccount(Account account) throws BusinessException{
        try(Connection connection = OracleConnection.getConnection()){
            String sql= "{call CREATEACCOUNT(?, ?, ?, ?)}";

            //fill in the ?'s

            CallableStatement callableStatement = connection.prepareCall(sql);

            callableStatement.setDouble(2, account.getBalance());
            callableStatement.setString(3, account.getAccountType());
            callableStatement.setString(4, account.getStatus());
            //register id because it is an OUT param
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            //callableStatement should have executed and now contains the ID param

//           customer.setId(callableStatement.getString(1));


        } catch (SQLException | ClassNotFoundException e) {
            throw new BusinessException("Unable to create account.");
        };
    }

    public Account getAccountById(String accountId) throws BusinessException{

        try(Connection connection = OracleConnection.getConnection()){

            String sql = "Select * FROM account WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, accountId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Account account = new Account();
                account.setBalance(rs.getDouble("balance"));
                account.setId(rs.getString("id"));
                return account;
            }


        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Account> getAccountsByUser(Customer customer) throws BusinessException{


        List<Account> accounts = null;

        try(Connection connection = OracleConnection.getConnection()){

            String sql = "Select * FROM CustomerAccount JOIN Account ON account.id = customeraccount.accountid AND customeraccount.customerid = ? AND account.status = 'ACTIVE'";

            PreparedStatement ps = connection.prepareStatement(sql);
            //TODO replace temporary hardcode
            //ps.setString(1, customer.getId());
            ps.setString(1, customer.getId());

            ResultSet rS = ps.executeQuery();
            accounts = new ArrayList<>();

            while(rS.next()){

                Account a = new Account();
                a.setId(rS.getString("accountid"));
                a.setAccountType(rS.getString("accounttype"));
                a.setStatus(rS.getString("status"));
                a.setBalance(rS.getDouble("balance"));

                System.out.println("balance for " + a.getId() + " : " + rS.getDouble("balance"));

                // FOR SOME REASON WITHDRAWING ADDS MONEY TO THE OPPOSITE ACCOUNT

                //TODO set customers at some point. Nerd.

                accounts.add(a);

                System.out.println("After accounts are added " + accounts);
            }

            return accounts;

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        throw new BusinessException("Could not fetch accounts.");
    }

    @Override
    public void updateAccount(Account account) throws BusinessException {
        try(Connection connection= OracleConnection.getConnection()){

            System.out.println("Inside updateAccount dao"+ account.getBalance());
            String sql="UPDATE account SET balance = ?, accounttype = ?, status = ? WHERE id = ?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getAccountType());
            ps.setString(3, account.getStatus());
            ps.setString(4, account.getId());

            //System.out.println(account.getBalance());

            ResultSet resultSet=ps.executeQuery();


        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Could not update account balance.");
        }
    }

    @Override
    public List<Account> getAllPendingAccounts() throws BusinessException {
        List<Account> accounts = null;

        try (Connection connection = OracleConnection.getConnection()) {

            String sql = "Select * FROM Account WHERE account.status = 'PENDING'";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rS = ps.executeQuery();
            accounts = new ArrayList<>();

            while (rS.next()) {
                Account account = new Account();

                account.setId(rS.getString("id"));
                account.setBalance(rS.getDouble("balance"));
                account.setAccountType(rS.getString("accounttype"));
                account.setStatus(rS.getString("status"));

                accounts.add(account);

            }
            return accounts;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

}


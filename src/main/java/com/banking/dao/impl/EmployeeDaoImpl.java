package com.banking.dao.impl;

import com.banking.dao.EmployeeDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

        @Override
        public Employee getEmployeeByLogin(Employee employee) throws BusinessException {
            Employee emp = null;
            try (Connection connection = OracleConnection.getConnection()) {
                String sql = "Select id, email, firstname, lastname from employee where email = ? AND password = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, employee.getEmail());
                ps.setString(2, employee.getPassword());

                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    emp = new Employee();

                    emp.setId(resultSet.getString("id"));
                    emp.setEmail(resultSet.getString("email"));
                    emp.setFirstName(resultSet.getString("firstname"));
                    emp.setLastName(resultSet.getString("lastname"));
                    System.out.println("You have been logged in. \nWelcome " + emp.getEmail());
                    return emp;
                } else {
                    throw new BusinessException("Employee does not exist");
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new BusinessException("Internal Error, please don't panic.");
            }
        }

    }
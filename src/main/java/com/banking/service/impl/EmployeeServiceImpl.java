package com.banking.service.impl;

import com.banking.dao.EmployeeDao;
import com.banking.dao.impl.EmployeeDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Employee;
import com.banking.service.EmployeeService;

import java.util.logging.Logger;


public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao dao = new EmployeeDaoImpl();
    final static Logger loggy = Logger.getLogger(String.valueOf(EmployeeDaoImpl.class));

    @Override
    public Employee employeeLogin(Employee employee) throws BusinessException {



        try{
            validatesEmployee(employee);
            employee = dao.getEmployeeByLogin(employee);
        } catch(BusinessException e) {
            loggy.warning(e.getMessage());
        }
        return employee;
    }

    @Override
    public void approveAccount(Customer customer, Account account) throws BusinessException {
        //update account to change status to ACTIVE from PENDING
        //create association between customer and account by calling customeraccountdao

    }

    public void validatesEmployee(Employee employee) throws BusinessException {
        if(employee == null){
            throw new BusinessException("Employee Object was not found.");
        } else if (!isValidEmail(employee.getEmail())){
            throw new BusinessException("Email is invalid.");
        } else if (!isValidPassword(employee.getPassword())) {
            throw new BusinessException("Password is invalid. Password must be between 4 - 20 characters, can contain numbers.");
        }
    }

    public boolean isValidUsername(String username){
        boolean b = false;
        if (username.matches("[a-zA-Z0-9]{2,10}")){
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
    private boolean isValidEmail(String email){
        boolean b = false;
        if (email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
            b = true;
        }
        return b;
    }
}

package com.banking.servlets;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.service.AccountService;
import com.banking.service.CustomerAccountService;
import com.banking.service.CustomerService;
import com.banking.service.impl.AccountServiceImpl;
import com.banking.service.impl.CustomerAccountServiceImpl;
import com.banking.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "approveAccountServlet")
public class ApproveAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Employee approve button sends here

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {

            String accountId = request.getParameter("accountid");

            List<Account> accounts = (List<Account>) session.getAttribute("accounts");

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId().equals(accountId)) {

                    Account approvedAccount = accounts.get(i);
                    approvedAccount.setStatus("ACTIVE");

                    try {

                        AccountService aService = new AccountServiceImpl();
                        CustomerAccountService cAService = new CustomerAccountServiceImpl();

                        //attach account to customer in customeraccount table
//                    cAService.createCustomerAccount(approvedAccount);
                        //update account status in database
                        aService.updateAccount(approvedAccount);


                        response.sendRedirect("http://localhost:9090/employeehome");


                    } catch (BusinessException e) {
                        e.printStackTrace();
                        response.sendRedirect("http://localhost:9090/employeehome");
                    }


                }
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("index.jsp");
        } else {

        }
    }
}


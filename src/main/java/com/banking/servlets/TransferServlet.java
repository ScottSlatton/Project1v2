package com.banking.servlets;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Transaction;
import com.banking.service.AccountService;
import com.banking.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TransferServlet")
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("viewCustomerHome.jsp");
        } else {

            //get account based off of dropdown selection from form
            Customer customer = (Customer) session.getAttribute("customer");
            List<Account> accounts = customer.getAccounts();


            //Make a new transaction object to update the accounts
            Transaction transaction = new Transaction();
            transaction.setAmount(Double.parseDouble(request.getParameter("transferAmount")));

            System.out.println(transaction.getAmount());


            for(int i = 0; i < accounts.size();i++){
                boolean isSelectedAccount = accounts.get(i).getId().equals(request.getParameter("transferAccount"));

                if (isSelectedAccount){
                        Account selectedAcct = accounts.get(i);
                    try{
                        transaction.setSender(selectedAcct);
                        transaction.setReceiver(selectedAcct);
                        AccountService aService = new AccountServiceImpl();
                        aService.deposit(selectedAcct, transaction);  //TODO check on deposit
                        response.sendRedirect("viewCustomerHome.jsp");

                    } catch (BusinessException e){
                        e.printStackTrace();
                        response.sendRedirect("viewCustomerHome.jsp");
                    }

                }

            }

        } response.sendRedirect("viewCustomerHome.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/transferForm.jsp").forward(request, response);
    }
}


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
            response.sendRedirect("index.jsp");
        } else {

            //get account based off of dropdown selection from form
            Customer customer = (Customer) session.getAttribute("customer");

            String senderId = request.getParameter("senderAccount");
            String receiverId = request.getParameter("receiverAccount");

            Account sender = new Account();
            sender.setId(senderId);


            Account receiver = new Account();
            receiver.setId(receiverId);

            //Make a new transaction object to update the accounts
            Transaction transaction = new Transaction();
            transaction.setAmount(Double.parseDouble(request.getParameter("transferAmount")));


            AccountService aService = new AccountServiceImpl();
            try {
                sender = aService.getAccount(sender);
                receiver = aService.getAccount(receiver);

                transaction.setSender(sender);
                transaction.setReceiver(receiver);

                aService.transfer(sender, receiver, transaction);

            } catch (BusinessException e) {
                e.printStackTrace();
            } finally {
                response.sendRedirect("viewCustomerHome.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("/transferForm.jsp").forward(request, response);
        }
    }
}


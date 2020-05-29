package com.banking.servlets;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.service.TransactionService;
import com.banking.service.impl.TransactionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerHomeServlet")
public class CustomerHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);


        if(session == null){
            response.sendRedirect("index.jsp");
        } else {

            if (session.getAttribute("customer") != null) {

                //TODO every time you go to the customer home page, get a fresh set of transactions.

//            Customer customer = (Customer) session.getAttribute("customer");
//            List<Account> accounts = customer.getAccounts();
//
//            TransactionService tService = new TransactionServiceImpl();
//            try {
//                tService.getTransactions(accounts);
//            } catch (BusinessException e) {
//                e.printStackTrace();
//            }

                request.getRequestDispatcher("/viewCustomerHome.jsp").forward(request, response);
            } else {
//            response.setStatus(404);
//            request.getRequestDispatcher("/404").forward(request, response);
                response.sendRedirect("http://localhost:9090/");
            }
        }
    }
}


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
import java.io.PrintWriter;

@WebServlet(name = "AccountApplicationServlet")
public class AccountApplicationServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("index.jsp");
        } else {

            String accountType = request.getParameter("accountType");

            Customer customer = (Customer) session.getAttribute("customer");

            Account account = new Account();

            account.setAccountType(accountType);
            account.setStatus("PENDING");
            account.setBalance(0.0);

            AccountService aService = new AccountServiceImpl();
            CustomerAccountService cAService = new CustomerAccountServiceImpl();

            try {
                aService.createAccount(account);

                System.out.println("this is in account app " + account);

                cAService.createCustomerAccount(customer, account);

//                PrintWriter out = response.getWriter();
//
//                out.println("You have successfully applied for a new bank account. We'll contact you when your account is approved.");
                System.out.println("You have successfully applied for a new bank account. We'll contact you when your account is approved.");
                response.sendRedirect("http://localhost:9090/customerhome");


            } catch (BusinessException e) {
                e.printStackTrace();
                response.sendRedirect("http://localhost:9090/");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("/accountApplicationForm.jsp").forward(request, response);
        }
    }
}

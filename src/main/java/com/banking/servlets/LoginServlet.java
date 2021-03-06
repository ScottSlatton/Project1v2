package com.banking.servlets;

import com.banking.dao.CustomerDao;
import com.banking.dao.impl.CustomerDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Customer;
import com.banking.service.CustomerService;
import com.banking.service.impl.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();


        Customer customer = new Customer();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        customer.setEmail(email);
        customer.setPassword(password);

        CustomerService cs = new CustomerServiceImpl();



        try {
            Customer loggedInCustomer = cs.customerLogin(customer);

            HttpSession session = request.getSession();

            session.setAttribute("customer", loggedInCustomer);

            System.out.println( loggedInCustomer + " successfully logged in");

            //Send logged in Customer to Unique show page

            response.sendRedirect("http://localhost:9090/customerhome");


        } catch (BusinessException e) {
            e.printStackTrace();
            response.sendRedirect("http://localhost:9090/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/customerLoginForm.jsp").forward(request, response);
    }
}

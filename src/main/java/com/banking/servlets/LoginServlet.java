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

@WebServlet(name = "loginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();

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






        //response.sendRedirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");



//
//
//        PrintWriter writer = response.getWriter();
//
//        String htmlResp = "<html>";
//        htmlResp += "<h2>Your email is: " + email + "<br/>";
//        htmlResp += "Your password is: " + password + "</h2>";
//        htmlResp += "</html>";
//
//        writer.println(htmlResp);

//        response.sendRedirect("http://localhost:9090/CustomerHomeServlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/customerLoginForm.jsp").forward(request, response);
    }
}

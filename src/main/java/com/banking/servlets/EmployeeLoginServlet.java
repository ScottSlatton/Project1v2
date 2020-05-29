package com.banking.servlets;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Employee;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import com.banking.service.EmployeeService;
import com.banking.service.impl.AccountServiceImpl;
import com.banking.service.impl.CustomerServiceImpl;
import com.banking.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Employee employee = new Employee();

        employee.setEmail(email);
        employee.setPassword(password);

        EmployeeService es = new EmployeeServiceImpl();



        try {
            // log in employee
            Employee loggedInEmployee = es.employeeLogin(employee);

            // grab accounts for employee to review
            AccountService aService = new AccountServiceImpl();



            List<Account> accounts = new ArrayList<>();




            //set session attributes
            HttpSession session = request.getSession();

            session.setAttribute("employee", loggedInEmployee);

            System.out.println( loggedInEmployee + " successfully logged in");

            //Send logged in Customer to Unique show page

            response.sendRedirect("http://localhost:9090/employeehome");


        } catch (BusinessException e) {
            e.printStackTrace();
            response.sendRedirect("http://localhost:9090/employeelogin");
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
        request.getRequestDispatcher("/employeeLoginForm.jsp").forward(request, response);
    }
}

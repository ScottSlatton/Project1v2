package com.banking.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EmployeeHomeServlet")
public class EmployeeHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("index.jsp");
        } else {

            if (session.getAttribute("employee") != null) {
                request.getRequestDispatcher("/viewEmployeeHome.jsp").forward(request, response);
            } else {
//            response.setStatus(404);
//            request.getRequestDispatcher("/404").forward(request, response);
                response.sendRedirect("http://localhost:9090/");
            }
        }
    }
}


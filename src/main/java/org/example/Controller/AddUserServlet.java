package org.example.Controller;

import org.example.DAO.UserDAO;
import org.example.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/login")
public class AddUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        if(userDAO.checkUser(username,email,name)) {
            response.sendRedirect("admin.jsp?error=Username Or Name Already Exists");
        }
        else {
            try {
                userDAO.addUser(username, password, email, role, name);
                response.sendRedirect("adminDashboard.jsp");
            } catch (Exception e) {
                response.sendRedirect("admin.jsp?error=An error occurred while processing your request");
            }
        }
    }
}

package org.example.Controller;

import org.example.DAO.UserDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID from the request parameter
        String userId = request.getParameter("userId");
        userDAO.deleteUser(Integer.parseInt(userId));
        // Redirect back to the deleteUser.jsp or any other appropriate page
        response.sendRedirect(request.getContextPath() + "/deleteUser.jsp");
    }
}

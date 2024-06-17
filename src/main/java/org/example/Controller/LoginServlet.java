package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.DAO.UserDAO;
import org.example.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private AttendanceDAO attendanceDAO;

    public void init() {
        userDAO = new UserDAO();
        attendanceDAO = new AttendanceDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            User user = userDAO.getUser(username, password, role);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                switch (role.toLowerCase()) {
                    case "admin":
                        response.sendRedirect("adminDashboard.jsp");
                        break;
                    case "student":
                        response.sendRedirect("student.jsp");
                        break;
                    case "professor":
                        // Retrieve attendance dates and set the request attribute
                        List<Date> dates = attendanceDAO.getAttendanceDates(user.getCourse());
                        request.setAttribute("dates", dates);

                        // Forward to ProfDashboard.jsp
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfDashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    default:
                        response.sendRedirect("login.jsp?error=Invalid role");
                        break;
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An error occurred while processing your request");
        }
    }
}

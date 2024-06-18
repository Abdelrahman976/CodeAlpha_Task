package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class BackServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;

    public void init() {
        attendanceDAO = new AttendanceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User obj = (User)request.getSession().getAttribute("user");
        List<Date> dates = attendanceDAO.getAttendanceDates(request.getParameter("course"));
        String error="";
        request.setAttribute("dates", dates);
        request.setAttribute("error", error);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfDashboard.jsp");
        dispatcher.forward(request, response);
    }

}

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

public class AttendanceCheckServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;

    public void init() {
        attendanceDAO = new AttendanceDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date = new Date(System.currentTimeMillis());
        User obj = (User)request.getSession().getAttribute("user");
        String Course = request.getParameter("course");
        System.out.println(attendanceDAO.getAttendanceByDate(Course, date));
        if(!attendanceDAO.getAttendanceByDate(Course, date).isEmpty()){
            List<Date> dates = attendanceDAO.getAttendanceDates(Course);
            String error="Attendance already taken for today";
            request.setAttribute("dates", dates);
            request.setAttribute("error", error);

            // Forward to ProfDashboard.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfDashboard.jsp");
            dispatcher.forward(request, response);
        }
        else{
            response.sendRedirect("prof.jsp");
        }
    }
}

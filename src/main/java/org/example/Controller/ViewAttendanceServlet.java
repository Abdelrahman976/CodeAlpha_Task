package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.Model.AttendanceRecord;
import org.example.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ViewAttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;

    public void init() {
        attendanceDAO = new AttendanceDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedDate = request.getParameter("selectedDate");

        // Store selected date in request attribute to forward to next JSP
        request.setAttribute("selectedDate", selectedDate);
        List<AttendanceRecord> attendanceRecords = attendanceDAO.getAttendanceByDate(request.getParameter("course"), Date.valueOf(selectedDate));
        request.setAttribute("attendance", attendanceRecords);
        // Forward to attendanceView.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewAttendance.jsp");
        dispatcher.forward(request, response);
    }

}

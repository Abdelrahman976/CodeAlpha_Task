package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.Model.AttendanceRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class EditAttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        String selectedDate = request.getParameter("selectedDate");
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        List<AttendanceRecord> attendanceRecords = attendanceDAO.getAttendanceByDate(request.getParameter("course") , Date.valueOf(selectedDate));
        request.setAttribute("attendanceRecords", attendanceRecords);
        request.setAttribute("selectedDate", selectedDate);
        request.getRequestDispatcher("editAttendance.jsp").forward(request, response);
    }
}

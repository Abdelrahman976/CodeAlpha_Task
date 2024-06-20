package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.DAO.CourseDAO;
import org.example.DAO.UserDAO;
import org.example.Model.Attendance;
import org.example.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;

public class SaveAttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;
    private CourseDAO courseDAO;

    public void init() {
        attendanceDAO = new AttendanceDAO();
        courseDAO = new CourseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        String date = request.getParameter("selectedDate");
        System.out.println(parameterNames);
        User obj = (User)request.getSession().getAttribute("user");
        String cid= courseDAO.getCidByCname(request.getParameter("course"));
        attendanceDAO.updateAttendance1(cid,Date.valueOf(date));
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();

            if (parameterName.startsWith("attendance")) {
                String userId = parameterName.substring(parameterName.indexOf("_") + 1);
                System.out.println(userId);
                System.out.println(date);
                String status="Present";
                attendanceDAO.updateAttendance2(Integer.parseInt(userId), Date.valueOf(date), status,cid);
            }
        }
        List<Date> dates = attendanceDAO.getAttendanceDates(request.getParameter("course"));
        request.setAttribute("dates", dates);

        // Forward to ProfDashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfDashboard.jsp");
        dispatcher.forward(request, response);
    }
}

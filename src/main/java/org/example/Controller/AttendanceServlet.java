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

public class AttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;
    private CourseDAO courseDAO;

    public void init() {
        attendanceDAO = new AttendanceDAO();
        courseDAO = new CourseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();

            if (parameterName.startsWith("attendance")) {
                String userId = parameterName.substring(parameterName.indexOf("_") + 1);
                Date date = new Date(System.currentTimeMillis());
                String status="Present";
                String cid= courseDAO.getCidByCname(request.getParameter("course"));
                attendanceDAO.takeAttendance(Integer.parseInt(userId), date, status,cid);
            }

        }
        // Handling the unchecked checkboxes for absent students
        User obj = (User)request.getSession().getAttribute("user");
        UserDAO temp= new UserDAO();
        List<User> students = null;
        try {
            students = temp.getStudents(request.getParameter("course"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i= 0 ;i<students.size();i++) {
            String userId = String.valueOf(students.get(i).getUser_id());
            if (request.getParameter("attendance_" + userId) == null) {
                Date date = new Date(System.currentTimeMillis());
                String status="Absent";
                String cid= courseDAO.getCidByCname(request.getParameter("course"));
                attendanceDAO.takeAttendance(Integer.parseInt(userId), date, status,cid);
            }
        }
        List<Date> dates = attendanceDAO.getAttendanceDates(request.getParameter("course"));
        request.setAttribute("dates", dates);

        // Forward to ProfDashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfDashboard.jsp");
        dispatcher.forward(request, response);
    }
}

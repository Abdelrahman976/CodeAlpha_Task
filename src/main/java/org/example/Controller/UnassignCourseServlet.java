package org.example.Controller;

import org.example.DAO.AttendanceDAO;
import org.example.DAO.UserDAO;
import org.example.DAO.CourseDAO;
import org.example.DAO.TakenCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnassignCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        int userId = Integer.parseInt(request.getParameter("userId"));
        String courseId = request.getParameter("courseId");

        // Validate if the user is assigned to the course
        TakenCourseDAO takenCourseDAO = new TakenCourseDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        boolean assigned = takenCourseDAO.isAssigned(userId, courseId);

        if (!assigned) {
            String errorMessage = "Selected user and course are not assigned together.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("unassignCourse.jsp").forward(request, response);
        } else {
            // Perform unassignment
            takenCourseDAO.removeAssignedCourse(userId, courseId);
            attendanceDAO.removeAttendance(userId, courseId);

            // Redirect back to unassignCourseForm.jsp with success message
            response.sendRedirect("unassignCourse.jsp?message=Course unassigned successfully");
        }
    }
}

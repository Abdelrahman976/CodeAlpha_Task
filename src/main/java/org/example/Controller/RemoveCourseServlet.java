package org.example.Controller;

import org.example.DAO.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve selected course name to delete
        String courseName = request.getParameter("courseName");

        // Call CourseDAO to delete the course
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.deleteCourse(courseName);

        // Redirect back to removeCourse.jsp with success message
        response.sendRedirect("removeCourse.jsp?message=Course removed successfully");
    }
}

package org.example.Controller;

import org.example.DAO.CourseDAO;
import org.example.Model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String courseId = request.getParameter("course_id");
        String courseName = request.getParameter("course_name");
        int creditHrs = Integer.parseInt(request.getParameter("credit_hrs"));

        // Create Course object
        Course course = new Course(courseId, courseName, creditHrs);

        // Call CourseDAO to add the course
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.addCourse(course);

        // Redirect back to addCourse.jsp with success message
        response.sendRedirect("addCourse.jsp?message=Course added successfully");
    }
}

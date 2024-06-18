package org.example.Controller;

import org.example.DAO.TakenCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AssignCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        int userId = Integer.parseInt(request.getParameter("userId"));
        String courseId = request.getParameter("courseId");
        TakenCourseDAO takenCourseDAO = new TakenCourseDAO();
        String errorMessage = "Selected user and course are already assigned together.";
        String message = "Course assigned successfully";
        if (takenCourseDAO.checkAssignCourse(userId, courseId)) {
            message=null;
        }
        // Call CourseDAO to assign the course
        else {
            takenCourseDAO.assignCourse(userId, courseId);
            errorMessage=null;
        }
        request.setAttribute("error", errorMessage);
        request.setAttribute("message", message);
        request.getRequestDispatcher("assignCourseForm.jsp").forward(request, response);
    }
}

package org.example.Controller;

import org.example.DAO.CourseDAO;
import org.example.DAO.TakenCourseDAO;
import org.example.DAO.UserDAO;
import org.example.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewClassesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve selected course ID from the form
        String courseId = request.getParameter("courseId");
        CourseDAO courseDAO= new CourseDAO();
        String CName=courseDAO.getCnameBycid(courseId);
        // Validate courseId (you may want additional validation here)

        // Retrieve users enrolled in the selected course from the database
        TakenCourseDAO ta = new TakenCourseDAO();
        List<User> users = ta.getUserByCName(courseId);

        // Forward users list to viewClasses.jsp for display
        request.setAttribute("users", users);
        request.setAttribute("courseName", CName);
        request.getRequestDispatcher("viewClasses.jsp").forward(request, response);
    }
}

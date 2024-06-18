<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.DAO.UserDAO, org.example.DAO.CourseDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.User" %>
<%@ page import="org.example.Model.Course" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Course</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <style>
        /* Reset and Base Styles */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            background-color: #f0f0f0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        /* Form Styling */
        form {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            width: 300px;
            max-width: 100%;
            text-align: center;
        }
        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
            color: #333;
        }
        select {
            width: calc(100% - 20px); /* Adjusted width to accommodate padding */
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }
        button {
            display: block;
            width: 100%;
            padding: 12px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
            margin-top: 20px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #c82333;
        }
        .message {
            color: green;
            margin-top: 10px;
        }
        .back-btn {
            position: absolute;
            top: 20px; /* Adjust top position */
            left: 20px; /* Adjust left position */
        }

        .back-btn a {
            display: block;
            padding: 10px;
            background-color: transparent;
            border: none;
            cursor: pointer;
            color: inherit;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-btn a:hover {
            color: #007bff;
        }

        .back-btn i {
            font-size: 1.5em;
        }
    </style>
</head>
<body>
<div class="back-btn">
    <a href="adminDashboard.jsp" aria-label="Back"><i class="fas fa-arrow-left"></i></a>
</div>

<form action="removeCourse" method="post">
    <h2>Remove Course</h2>
    <label for="courseName">Select Course:</label>
    <select id="courseName" name="courseName" required>
        <%
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courseNames = courseDAO.getAllCourses();
            for (Course courseName : courseNames) {
        %>
        <option value="<%= courseName.getCourseName() %>"><%= courseName.getCourseName() %></option>
        <% } %>
    </select><br>
    <button type="submit">Delete Course</button>
    <% if (request.getParameter("message") != null) { %>
    <p class="message"><%= request.getParameter("message") %></p>
    <% } %>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, org.example.DAO.CourseDAO, org.example.DAO.UserDAO, org.example.Model.User" %>
<%@ page import="org.example.Model.Course" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Classes</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            margin-top: 0;
            color: #007bff;
        }
        form {
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            padding: 10px 20px;
            font-size: 1em;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-left: 10px; /* Added margin for consistent spacing */
        }
        button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .course-header {
            margin-top: 10px;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-size: 20px;
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
        /* Back button styles */
        /* Style for back button */
        .back-btn {
            position: absolute;
            top: 20px; /* Adjust top position */
            left: 20px; /* Adjust left position */
        }

        /* Style for the button inside the anchor tag */
        .back-btn button {
            padding: 15px; /* Increase padding for larger size */
            background-color: transparent; /* Transparent background */
            border: none; /* Remove default border */
            color: inherit; /* Inherit text color from parent */
            cursor: pointer; /* Change cursor to pointer on hover */
            transition: color 0.3s ease; /* Smooth transition for color change */
        }

        /* Style for the arrow icon */
        .back-btn button i {
            font-size: 1.5em; /* Increase icon size */
        }

        /* Hover effect for button */
        .back-btn button:hover {
            color: #007bff; /* Change text color on hover */
        }
    </style>
</head>
<body>
<div class="back-btn">
    <a href="adminDashboard.jsp" aria-label="Back"><i class="fas fa-arrow-left"></i></a>
</div>
<div class="container">
    <h2>View Classes</h2>

    <form action="viewClasses" method="post">
        <div class="form-group">
            <label for="courseId">Select Course:</label>
            <select id="courseId" name="courseId">
                <%
                    CourseDAO courseDAO = new CourseDAO();
                    List<Course> courseNames = courseDAO.getAllCourses();
                    for (Course courseName : courseNames) {
                %>
                <option value="<%= courseName.getCid() %>"><%= courseName.getCourseName() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn add-btn">View</button> <!-- Added class for consistent styling -->
    </form>

    <%-- Display course name if users are fetched --%>
    <% if (request.getAttribute("users") != null) {
        String selectedCourse = (String) request.getAttribute("courseName");
    %>
    <div class="course-header">
        <h3>Course:  <%= selectedCourse %></h3>
    </div>
    <table>
        <thead>
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <% List<User> users = (List<User>) request.getAttribute("users");
            for (User user : users) { %>
        <tr>
            <td><%= user.getUser_id() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getRole() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

</div>
</body>
</html>

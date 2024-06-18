<%@ page import="org.example.Model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% User obj = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Professor Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            max-width: 800px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
            margin-top: 0;
        }
        h3 {
            color: #343a40;
            margin-top: 20px;
        }
        hr {
            border: none;
            border-top: 1px solid #dee2e6;
            margin: 20px 0;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            font-size: 1em;
        }
        .button:hover {
            background-color: #0056b3;
        }
        form {
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            margin-right: 10px;
            color: #343a40;
        }
        select {
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .error {
            color: red;
        }
        /* Back button styles */
        .back-btn {
            position: absolute;
            top: 20px;
            left: 20px;
            z-index: 9999;
        }

        .back-btn a {
            display: block;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .back-btn a i {
            margin-right: 5px;
        }

        .back-btn a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="back-btn">
    <a href="login.jsp">
        <i class="fas fa-arrow-left"></i> Back to Login
    </a>
</div>
<div class="container">
    <h2>Welcome, <%= obj.getUsername() %>!</h2>
    <h3>Course: <%= session.getAttribute("course") %></h3>
    <hr>
    <h3>Take Attendance:</h3>
    <form action="checkatt" method="post">
        <input type="hidden" name="course" value="<%= session.getAttribute("course") %>">
        <button type="submit" class="button">Take Attendance for Today</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <hr>
    <h3>View Attendance History:</h3>
    <form action="viewAttendanceServlet" method="post">
        <input type="hidden" name="course" value="<%= session.getAttribute("course") %>">
        <label for="selectDate">Select Date:</label>
        <select id="selectDate" name="selectedDate">
            <% List<Date> dates=(List<Date>)request.getAttribute("dates");%>
            <% for (int i = 0; i < dates.size(); i++) {
                java.time.LocalDate date = dates.get(i).toLocalDate();
                String formattedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            %>
            <option value="<%= formattedDate %>"><%= formattedDate %></option>
            <% } %>
        </select>
        <button type="submit" class="button">View Attendance</button>
    </form>
</div>
</body>
</html>

<%@ page import="org.example.Model.User" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.AttendanceRecord" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% User obj =(User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance View</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
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
        h3 {
            margin-top: 0;
            color: #007bff;
        }
        p {
            font-size: 1.1em;
            margin: 5px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th, td {
            border: 1px solid #dee2e6;
            padding: 15px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        tr:hover {
            background-color: #e9ecef;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            font-size: 1em;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        /* Define styles for different attendance statuses */
        .status-present {
            background-color: #28a745; /* Green color for Present */
            color: white;
        }
        .status-absent {
            background-color: #dc3545; /* Red color for Absent */
            color: white;
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
<div class="container">
    <!-- Back button with arrow icon -->
    <form action="back" method="post">
        <input type="hidden" name="course" value="<%= session.getAttribute("course") %>">
        <a href="ProfDashboard.jsp" class="back-btn"><button type="submit"><i class="fas fa-arrow-left"></i></button></a>
    </form>

    <h3>Course Details:</h3>
    <p><strong>Course Name:</strong> <%= session.getAttribute("course") %></p>
    <p><strong>Professor Name:</strong> <%= obj.getName() %></p>
    <p><strong>Date:</strong> <%= request.getAttribute("selectedDate") %></p>

    <h3>Students Attendance:</h3>
    <table>
        <thead>
        <tr>
            <th>Student ID</th>
            <th>Student Name</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <% List<AttendanceRecord> data =(List<AttendanceRecord>)request.getAttribute("attendance"); %>
        <% for (int i = 0; i < data.size(); i++) { %>
        <tr>
            <td><%= data.get(i).getUserId() %></td>
            <td><%= data.get(i).getUsername() %></td>
            <td class="<%= data.get(i).getAttendanceStatus().equals("Present") ? "status-present" : "status-absent" %>">
                <%= data.get(i).getAttendanceStatus() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <!-- Form to submit selected date -->
    <form id="editAttendanceForm" action="editAttendance" method="post">
        <input type="hidden" name="selectedDate" value="<%= request.getAttribute("selectedDate") %>">
        <input type="hidden" name="course" value="<%= session.getAttribute("course") %>">
        <button type="submit" class="btn">Edit Attendance</button>
    </form>
</div>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.Attendance" %>
<%@ page import="org.example.DAO.AttendanceDAO" %>
<%@ page import="org.example.Model.User" %>

<%
    // Simulating the student data
    User obj = (User) request.getSession().getAttribute("user");
    String username = obj.getUsername();
    String course = (String) request.getSession().getAttribute("course");
    int userId = (Integer) obj.getUser_id();

    // Fetch attendance records
    AttendanceDAO attendanceDAO = new AttendanceDAO();
    List<Attendance> attendanceList = attendanceDAO.getAttendanceByUserId(userId, course);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Attendance History</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background-color: #f4f4f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Ensure full height of viewport */
        }

        .header {
            text-align: center;
            background: linear-gradient(135deg, #007BFF, #0056b3);
            color: white;
            padding: 20px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .header .student-name {
            font-size: 1.8em;
            margin-bottom: 10px;
        }

        .header .course-name {
            font-size: 1.2em;
            margin-top: 10px;
        }

        .back-btn {
            position: absolute;
            top: 20px;
            left: 20px;
        }

        .back-btn a {
            display: inline-block;
            padding: 10px;
            background-color: transparent;
            border: none;
            cursor: pointer;
            color: white;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-btn a i {
            font-size: 1.8em;
        }

        .back-btn a:hover {
            color: #c7d2fe;
        }

        .container {
            flex: 1; /* Take remaining vertical space */
            max-width: 100%;
            margin: 30px 200px 30px 200px; /* Center the container horizontally with auto margin */
            padding: 20px;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            border: 1px solid #ddd;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: white;
            font-size: 1.1em;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .status-present {
            background-color: #28a745;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
        }

        .status-absent {
            background-color: #dc3545;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
        }

        .footer {
            text-align: center;
            padding: 10px 0;
            background-color: #007BFF;
            color: white;
            font-size: 0.9em;
            border-radius: 0 0 10px 10px;
            margin-top: auto; /* Push footer to bottom */
            width: 100%; /* Full width */
        }
    </style>
</head>
<body>
<div class="header">
    <div class="student-name">Attendance History for <%= username %></div>
    <div class="course-name">Course: <%= course %></div>
    <div class="back-btn">
        <a href="login.jsp">
            <i class="fas fa-arrow-left"></i>
        </a>
    </div>
</div>

<div class="container">
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <% for (Attendance attendance : attendanceList) { %>
        <tr>
            <td><%= attendance.getDate() %></td>
            <td class="<%= attendance.getStatus().equals("Present") ? "status-present" : "status-absent" %>">
                <%= attendance.getStatus() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<div class="footer">
    &copy; 2024 Student Attendance System. All rights reserved.
</div>
</body>
</html>

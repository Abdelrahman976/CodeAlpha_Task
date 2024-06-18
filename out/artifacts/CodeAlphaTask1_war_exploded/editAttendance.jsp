<%@ page import="org.example.Model.User" %>
<%@ page import="org.example.Model.AttendanceRecord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% User obj = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Professor Dashboard</title>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #343a40;
            color: white;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .header .prof-name, .header .course-name, .header .date {
            font-size: 1.5em;
        }
        .header .date {
            font-size: 1.2em;
        }
        .container {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        table, th, td {
            border: 1px solid #dee2e6;
        }
        th, td {
            padding: 15px;
            text-align: left;
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
        button {
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="prof-name"><%= obj.getName() %></div>
    <div class="date"><%= request.getAttribute("selectedDate") %></div>
    <div class="course-name"><%= session.getAttribute("course") %></div>
</div>

<div class="container">
    <form action="SaveAttendance" method="post">
        <input type="hidden" name="course" value="<%= session.getAttribute("course") %>">
        <input type="hidden" name="selectedDate" value="<%= request.getAttribute("selectedDate") %>">
        <table>
            <tr>
                <th>Student Name</th>
                <th>Attendance</th>
            </tr>
            <%List<AttendanceRecord> attendanceRecords=(List<AttendanceRecord>) request.getAttribute("attendanceRecords"); %>
            <c:forEach var="record" items="${attendanceRecords}">
                <tr>
                    <td>${record.username}</td>
                    <td>
                        <c:choose>
                            <c:when test="${record.attendanceStatus == 'Present'}">
                                <input type="checkbox" name="attendance_${record.userId}" value="${record.userId}" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="attendance_${record.userId}" value="${record.userId}">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit">Save Attendance</button>
    </form>
</div>
</body>
</html>

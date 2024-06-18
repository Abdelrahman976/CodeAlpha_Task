<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.User" %>
<%@ page import="org.example.DAO.UserDAO" %>


<%
    // Simulating the professor and students data
    UserDAO temp = new UserDAO();
    User obj = (User)request.getSession().getAttribute("user");
    String professor = obj.getName();
    List<User> students = null;
    try {
        students = temp.getStudents(session.getAttribute("course").toString());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    String courseName = session.getAttribute("course").toString();

    // Getting the current date
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String currentDate = formatter.format(new Date());
%>

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
    <div class="prof-name"><%= professor %></div>
    <div class="date"><%= currentDate %></div>
    <div class="course-name"><%= courseName %></div>
</div>

<div class="container">
    <form action="markAttendance" method="post">
        <input type="hidden" name="course" value="<%= courseName %>">
        <table>
            <tr>
                <th>Student Name</th>
                <th>Attendance</th>
            </tr>
            <% for (int i = 0; i < students.size(); i++) { %>
            <tr>
                <td><%= students.get(i).getName() %></td>
                <td><input type="checkbox" name="attendance_<%= students.get(i).getUser_id() %>" value="<%= students.get(i).getUser_id() %>"></td>
            </tr>
            <% } %>
        </table>
        <button type="submit">Submit Attendance</button>
    </form>
</div>
</body>
</html>


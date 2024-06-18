<%@ page import="org.example.DAO.CourseDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }
        input:focus, select:focus {
            outline: none;
            border-color: #007BFF;
        }
        button {
            display: block;
            width: 100%;
            padding: 12px;
            background-color: #007BFF;
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
            background-color: #0056b3;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
        /* Manage System Button */
        .manage-system {
            position: absolute;
            top: 10px;
            right: 10px;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
            text-transform: uppercase;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        .manage-system:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<a href="adminlogin.jsp" class="manage-system">Manage System</a>
<form action="login" method="post">
    <h2>Login</h2>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="role">Role:</label>
    <select id="role" name="role">
        <option value="student">Student</option>
        <option value="professor">Professor</option>
    </select><br>
    <label for="course">Course:</label>
    <select id="course" name="course">
        <%
            // Fetch course names from the database
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courses = courseDAO.getAllCourses();
            for (Course course : courses) {
        %>
        <option value="<%= course.getCourseName() %>"><%= course.getCourseName() %></option>
        <%
            }
        %>
    </select><br>
    <button type="submit">Login</button>
    <% if (request.getParameter("error") != null) { %>
    <p class="error"><%= request.getParameter("error") %></p>
    <% } %>
</form>
</body>
</html>


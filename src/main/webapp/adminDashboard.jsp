<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
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
        h2 {
            margin-top: 0;
            color: #007bff;
        }
        p {
            font-size: 1.1em;
            margin: 5px 0;
        }
        .btn-container {
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 1em;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
            margin-right: 10px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .add-btn {
            background-color: #28a745;
        }
        .delete-btn {
            background-color: #dc3545;
        }
        .view-btn {
            background-color: #007bff;
            margin-top: 10px;
        }
        .back-btn {
            position: absolute;
            top: 20px;
            left: 20px;
            color: #007bff;
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
<div class="container">
    <!-- Back button with arrow icon -->
    <div class="back-btn">
        <a href="login.jsp" aria-label="Back"><i class="fas fa-arrow-left"></i></a>
    </div>

    <div class="admin-info">
        <h2>Welcome, Admin!</h2>
    </div>
    <div class="btn-container">
        <a href="admin.jsp" class="btn add-btn">Add User</a>
        <a href="deleteUser.jsp" class="btn delete-btn">Delete User</a>
        <a href="addCourse.jsp" class="btn add-btn">Add Course</a>
        <a href="removeCourse.jsp" class="btn delete-btn">Remove Course</a>
        <a href="assignCourseForm.jsp" class="btn add-btn">Assign Course</a>
        <a href="unassignCourse.jsp" class="btn delete-btn">Unassign Course</a>
        <a href="viewClasses.jsp" class="btn view-btn">View Classes</a> <!-- Modified button -->
    </div>
</div>
</body>
</html>

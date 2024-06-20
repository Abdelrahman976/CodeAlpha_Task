# Student Attendance Management System

## Overview

A web-based system for managing attendance in educational institutions. It follows the MVC architecture, uses Maven as the build tool, and is developed with Servlets and JSP. The application is deployed on Apache Tomcat.

## Project Structure

The project follows the Model-View-Controller (MVC) architecture.


### Key Components

- **Authentication Filters**: Manage access for admins, professors, and students.
- **Controllers**: Handle course management, user management, attendance, and login.
- **DAO**: Manage database interactions for attendance, courses, and users.
- **Models**: Represent data structures like Attendance, Course, and User.
- **Utilities**: Handle database connections.

### JSP Pages

- **Admin Pages**: `admin.jsp`, `adminDashboard.jsp`, `addCourse.jsp`, `deleteUser.jsp`, `editAttendance.jsp`,`viewClasses.jsp`
- **Professor Pages**: `prof.jsp`, `ProfDashboard.jsp`, `assignCourseForm.jsp`, `removeCourse.jsp`, `ViewAttendance.jsp`
- **Student Pages**: `student.jsp`
- **Common Pages**: `index.jsp`, `login.jsp`, `adminlogin.jsp`

## Getting Started

### Prerequisites

- JDK 8 or higher
- Apache Tomcat 9 or higher
- MySQL or compatible database

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/CodeAlpha_Task.git
    ```
2. **Import the project into your IDE.**
3. **Set up the database:**
    - Create a new database in MySQL.
    - Import SQL scripts in the `resources` directory.
4. **Configure the database connection:**
    - Update `DatabaseConnection.java` with your database credentials.
5. **Deploy the project on Apache Tomcat.**
6. **Run the application:**
    - Access it at `http://localhost:8080/attendance-management-system`.

## Usage

- **Admin**: Manage users, courses, and assign/unassign courses.
- **Professor**: Manage and mark attendance, view attendance records.
- **Student**: View attendance records.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## Acknowledgements

This project was completed by me as a task during the CodeAlpha Java Programming internship.



package org.example.DAO;

import org.example.Model.Course;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Add a course
    public void addCourse(Course course) {
        String sql = "INSERT INTO course (cid, course_name, credit_hrs) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCid());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCreditHrs());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete a course
    public void deleteCourse(String courseName) {
        String sql = "DELETE FROM course WHERE course_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT cid, course_name, credit_hrs FROM course";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String cid = rs.getString("cid");
                String courseName = rs.getString("course_name");
                int creditHrs = rs.getInt("credit_hrs");

                Course course = new Course(cid, courseName, creditHrs);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public String getCidByCname(String courseName) {
        String sql = "SELECT cid FROM course WHERE course_name = ?";
        String cid = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cid = rs.getString("cid");
                return cid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getCnameBycid(String cid) {
        String sql = "SELECT course_name FROM course WHERE cid = ?";
        String courseName = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                courseName = rs.getString("course_name");
                return courseName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


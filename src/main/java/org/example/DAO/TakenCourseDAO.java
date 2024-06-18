package org.example.DAO;

import org.example.Model.Course;
import org.example.Model.User;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TakenCourseDAO {
    // Assign course to a user
    public void assignCourse(int userId, String courseId) {
        String sql = "INSERT INTO taken_courses (user_id, course_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Remove assigned course from a user
    public void removeAssignedCourse(int userId, String courseId) {
        String sql = "DELETE FROM taken_courses WHERE user_id = ? AND course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUserAssignedToCourse(String username, String password, String role, String courseName) {
        String sql = "SELECT * FROM users u " +
                "JOIN taken_courses tc ON u.user_id = tc.user_id " +
                "JOIN course c ON tc.course_id = c.cid " +
                "WHERE u.username = ? AND u.password = ? AND u.role = ? AND c.course_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setString(4, courseName);
            ResultSet rs = stmt.executeQuery();

            return rs.next();  // Return true if a record is found, otherwise false
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean isAssigned(int userId, String courseId) {
        String sql = "SELECT COUNT(*) AS count FROM taken_courses WHERE user_id = ? AND course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, courseId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public boolean checkAssignCourse(int userId, String courseId) {
        String sql = "SELECT * FROM taken_courses WHERE user_id = ? AND course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, courseId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public List<User> getUserByCName(String cid) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users u " +
                "JOIN taken_courses tc ON u.user_id = tc.user_id " +
                "WHERE tc.course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cid);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }
    public boolean checkAssignUser(int userId) {
        String sql = "SELECT * FROM taken_courses WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}


package org.example.DAO;

import org.example.Model.Attendance;
import org.example.Model.User;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {
    public User getUser(String username, String password, String role) throws Exception {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setCourse(resultSet.getString("course"));
            }
        }
        return user;
    }
    public void addUser(String username, String password, String email, String role, String name, String course) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.setString(4, name);
            statement.setString(5, email);
            statement.setString(6, course);

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }
    public void deleteUser(int user_id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }
    public List<User> getStudents(String courseName) throws Exception {
        List<User> Students = new java.util.ArrayList<>(List.of());
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE course = ? AND role = 'student'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User Student = new User();
                Student.setUser_id(resultSet.getInt("user_id"));
                Student.setUsername(resultSet.getString("username"));
                Student.setPassword(resultSet.getString("password"));
                Student.setRole(resultSet.getString("role"));
                Student.setName(resultSet.getString("name"));
                Student.setEmail(resultSet.getString("email"));
                Student.setCourse(resultSet.getString("course"));
                Students.add(Student);
            }
        }
        return Students;
    }
    public static List<User> getAllUsers() throws Exception {
        List<User> Users = new java.util.ArrayList<>(List.of());
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setCourse(resultSet.getString("course"));
                Users.add(user);
            }
        }
        return Users;
    }

}

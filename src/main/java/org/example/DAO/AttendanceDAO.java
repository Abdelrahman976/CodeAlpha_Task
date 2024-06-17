package org.example.DAO;

import org.example.Model.Attendance;
import org.example.Model.AttendanceRecord;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public List<Attendance> getAttendanceByUserId(int userId) throws Exception {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM attendance WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(resultSet.getInt("attendance_id"));
                attendance.setUserId(resultSet.getInt("user_id"));
                attendance.setDate(resultSet.getDate("date"));
                attendance.setStatus(resultSet.getString("status"));
                attendances.add(attendance);
            }
        }
        return attendances;
    }
    public void takeAttendance(int userId, Date date, String status) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO attendance VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setDate(2, date);
            statement.setString(3, status);

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new attendance was inserted successfully!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inserting attendance: " + e.getMessage(), e);
        }
    }

    public List<AttendanceRecord> getAttendanceByDate(String course, Date date) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            List<AttendanceRecord> attendanceRecords = new ArrayList<>();
            String sql = "SELECT * FROM attendance a join users u on a.user_id=u.user_id WHERE course = ? AND date = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, course);
            statement.setDate(2, date);

            // Execute the insert statement
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AttendanceRecord attendanceRecord = new AttendanceRecord();
                attendanceRecord.setUserId(resultSet.getInt("user_id"));
                attendanceRecord.setUsername(resultSet.getString("username"));
                attendanceRecord.setAttendanceStatus(resultSet.getString("status"));
                attendanceRecords.add(attendanceRecord);
            }
            return attendanceRecords;
        } catch (Exception e) {
            throw new RuntimeException("Error inserting attendance: " + e.getMessage(), e);
        }
    }
    public List<Date> getAttendanceDates(String course) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            List<Date> dates = new ArrayList<>();
            String sql = "SELECT DISTINCT date FROM attendance a join users u on a.user_id=u.user_id WHERE course = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, course);

            // Execute the insert statement
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dates.add(resultSet.getDate("date"));
            }
            return dates;
        } catch (Exception e) {
            throw new RuntimeException("Error inserting attendance: " + e.getMessage(), e);
        }
    }
    public void updateAttendance1(String course) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql1="UPDATE attendance SET status = ? " +
                    "WHERE user_id IN (SELECT u.user_id FROM users u WHERE u.course = ?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, "Absent");
            statement1.setString(2, course);
            int rowsInserted1 = statement1.executeUpdate();
            if (rowsInserted1 > 0) {
                System.out.println("update made successfully!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inserting attendance: " + e.getMessage(), e);
        }
    }
    public void updateAttendance2(int userId, Date date, String status) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql2 = "UPDATE attendance SET status = ? WHERE user_id = ? AND date = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, status);
            statement2.setInt(2, userId);
            statement2.setDate(3, date);

            // Execute the insert statement
            int rowsInserted2 = statement2.executeUpdate();
            if (rowsInserted2 > 0) {
                System.out.println("A new attendance was inserted successfully!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inserting attendance: " + e.getMessage(), e);
        }
    }
}

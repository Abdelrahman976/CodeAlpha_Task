package org.example.Model;

public class AttendanceRecord {
    private int userId;
    private String username;
    private String attendanceStatus;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }
}

package org.example.Model;
import java.util.Date;

public class Attendance {
    private int attendanceId;
    private int userId;
    private Date date;
    private String status;
    private String cid;

    public Attendance() {
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCid() {return cid;}

    public void setCid(String cid) {this.cid = cid;}
}
package org.example.Model;

public class Course {
    private String cid;
    private String courseName;
    private int creditHrs;

    // Constructor
    public Course(String cid, String courseName, int creditHrs) {
        this.cid = cid;
        this.courseName = courseName;
        this.creditHrs = creditHrs;
    }

    // Default constructor
    public Course() {
    }

    // Getter for cid
    public String getCid() {
        return cid;
    }

    // Setter for cid
    public void setCid(String cid) {
        this.cid = cid;
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Setter for courseName
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Getter for creditHrs
    public int getCreditHrs() {
        return creditHrs;
    }

    // Setter for creditHrs
    public void setCreditHrs(int creditHrs) {
        this.creditHrs = creditHrs;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + '\'' +
                ", courseName='" + courseName + '\'' +
                ", creditHrs=" + creditHrs +
                '}';
    }
}

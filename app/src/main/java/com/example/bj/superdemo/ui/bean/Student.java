package com.example.bj.superdemo.ui.bean;

import java.util.List;

/**
 * Created by bj on 2017-1-5.
 * Description：
 */

public class Student {
    private String studentId;
    private String studentNam;

    public List<Course> getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(List<Course> studentCourse) {
        this.studentCourse = studentCourse;
    }

    private List<Course> studentCourse;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentNam() {
        return studentNam;
    }

    public void setStudentNam(String studentNam) {
        this.studentNam = studentNam;
    }

}

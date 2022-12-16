package com.projectsiakad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "mst_course")
public class Course {

    @Id
    @Column(name = "course_code")
    @NotBlank
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credits_course")
    private Integer creditsCourse;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCreditsCourse() {
        return creditsCourse;
    }

    public void setCreditsCourse(Integer creditsCourse) {
        this.creditsCourse = creditsCourse;
    }
}

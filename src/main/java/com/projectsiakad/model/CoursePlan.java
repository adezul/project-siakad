package com.projectsiakad.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_course_plan")
public class CoursePlan {
    @Id
    @Column(name = "course_plan_id")
    @NotBlank
    private Integer coursePlanId;

    private Boolean isAccept;

    @OneToOne
    @JoinColumn(name = "npm")
    private Student student;

    public Integer getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(Integer coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public Boolean getAccept() {
        return isAccept;
    }

    public void setAccept(Boolean accept) {
        isAccept = accept;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

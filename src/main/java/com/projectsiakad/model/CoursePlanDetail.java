package com.projectsiakad.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_course_plan_detail")
public class CoursePlanDetail {

    @Id
    @Column(name = "course_plan_detail_id")
    @NotBlank
    private Integer coursePlanDetailId;

    @ManyToOne
    @JoinColumn(name = "course_plan_id")
    private CoursePlan coursePlan;

    @ManyToOne
    @JoinColumn(name = "course_code")
    private Course course;

    public Integer getCoursePlanDetailId() {
        return coursePlanDetailId;
    }

    public void setCoursePlanDetailId(Integer coursePlanDetailId) {
        this.coursePlanDetailId = coursePlanDetailId;
    }

    public CoursePlan getCoursePlan() {
        return coursePlan;
    }

    public void setCoursePlan(CoursePlan coursePlan) {
        this.coursePlan = coursePlan;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

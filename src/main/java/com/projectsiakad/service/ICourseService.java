package com.projectsiakad.service;

import com.projectsiakad.model.Course;
import org.springframework.data.domain.Page;

public interface ICourseService {
    Page<Course> listCourse(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;

    Course createCourse(Course course) throws Exception;

    void deleteById(String courseCode) throws Exception;

    Course updateById(String courseCode, Course course) throws Exception;
}

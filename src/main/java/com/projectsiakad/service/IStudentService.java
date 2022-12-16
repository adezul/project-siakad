package com.projectsiakad.service;

import com.projectsiakad.model.Student;
import org.springframework.data.domain.Page;

public interface IStudentService {

    Page<Student> listStudent(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;

    Student createStudent(Student student) throws Exception;

    void deleteById(Integer npm) throws Exception;

    Student updateById(Integer npm, Student student) throws Exception;
}

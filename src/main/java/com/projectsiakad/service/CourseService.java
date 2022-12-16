package com.projectsiakad.service;

import com.projectsiakad.model.Course;
import com.projectsiakad.model.Major;
import com.projectsiakad.repository.ICourseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class CourseService implements ICourseService{

    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Page<Course> listCourse(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course createCourse(Course course) throws Exception {
        try {
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public void deleteById(String courseCode) throws Exception {
        courseRepository.deleteById(courseCode);
    }

    @Override
    public Course updateById(String courseCode, Course course) throws Exception {
        Optional<Course> exsistingCourse = courseRepository.findById(courseCode);

        if (course.getCourseCode().equals(null) || (course.getCourseCode().equals(exsistingCourse.get().getCourseCode()))) {
            course.setCourseCode(exsistingCourse.get().getCourseCode());
        }
        else
            exsistingCourse.get().setCourseName(course.getCourseName());
        Course result = courseRepository.save(exsistingCourse.get());
        return result;
    }
}

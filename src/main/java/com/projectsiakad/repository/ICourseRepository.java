package com.projectsiakad.repository;

import com.projectsiakad.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, String> {
}

package com.projectsiakad.repository;

import com.projectsiakad.model.CoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoursePlanRepository extends JpaRepository<CoursePlan, Integer> {
}

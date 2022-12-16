package com.projectsiakad.service;

import com.projectsiakad.model.CoursePlan;
import org.springframework.data.domain.Page;

public interface ICoursePlanService {

    Page<CoursePlan> listCoursePlan(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;

    CoursePlan createCoursePlan(CoursePlan coursePlan) throws Exception;

    void deleteById(Integer coursePlanId) throws Exception;
}

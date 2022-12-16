package com.projectsiakad.service;

import com.projectsiakad.model.CoursePlan;
import com.projectsiakad.model.CoursePlanDetail;
import org.springframework.data.domain.Page;

public interface ICoursePlanDetailService {

    Page<CoursePlanDetail> listCoursePlanDetail(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;

    CoursePlanDetail createCoursePlanDetail(CoursePlanDetail coursePlanDetail) throws Exception;

    void deleteById(Integer coursePlanDetailId) throws Exception;
}

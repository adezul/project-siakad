package com.projectsiakad.service;

import com.projectsiakad.model.CoursePlan;
import com.projectsiakad.model.CoursePlanDetail;
import com.projectsiakad.model.CoursePlanDetailRepository;
import com.projectsiakad.repository.ICoursePlanDetailRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class CoursePlanDetailService implements ICoursePlanDetailService{

    private ICoursePlanDetailRepository coursePlanDetailRepository;
    private final CoursePlanDetailRepository coursePlanDetailRepository;

    public CoursePlanDetailService(ICoursePlanDetailRepository coursePlanDetailRepository,
                                   CoursePlanDetailRepository coursePlanDetailRepository) {
        this.coursePlanDetailRepository = coursePlanDetailRepository;
        this.coursePlanDetailRepository = coursePlanDetailRepository;
    }

    @Override
    public Page<CoursePlanDetail> listCoursePlanDetail(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return coursePlanDetailRepository.findAll(pageable);
    }

    @Override
    public CoursePlanDetail createCoursePlanDetail(CoursePlanDetail coursePlanDetail) throws Exception {
        try {
            CoursePlanDetail newCoursePlanDetail = coursePlanDetailRepository.save(coursePlanDetail);
            return newCoursePlanDetail;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public void deleteById(Integer coursePlanDetailId) throws Exception {
        coursePlanDetailRepository.deleteById(coursePlanDetailId);
    }
}

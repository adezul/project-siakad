package com.projectsiakad.service;

import com.projectsiakad.model.CoursePlan;
import com.projectsiakad.repository.ICoursePlanRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class CoursePlanService implements ICoursePlanService {

    private ICoursePlanRepository coursePlanRepository;

    public CoursePlanService(ICoursePlanRepository coursePlanRepository) {
        this.coursePlanRepository = coursePlanRepository;
    }


    @Override
    public Page<CoursePlan> listCoursePlan(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return coursePlanRepository.findAll(pageable);
    }

    @Override
    public CoursePlan createCoursePlan(CoursePlan coursePlan) throws Exception {
        try {
            CoursePlan newCoursePlan = coursePlanRepository.save(coursePlan);
            return newCoursePlan;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public void deleteById(Integer coursePlanId) throws Exception {
        coursePlanRepository.deleteById(coursePlanId);
    }

}

package com.projectsiakad.controller;

import com.projectsiakad.model.CoursePlan;
import com.projectsiakad.model.response.ErrorResponse;
import com.projectsiakad.model.response.PagingResponse;
import com.projectsiakad.model.response.SuccessResponse;
import com.projectsiakad.service.ICoursePlanService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course-plans")
@Validated
public class CoursePlanController {

    private ICoursePlanService coursePlanService;

    public CoursePlanController(ICoursePlanService coursePlanService) {
        this.coursePlanService = coursePlanService;
    }

    @PostMapping
    public ResponseEntity createCoursePlan(@Valid @RequestBody CoursePlan coursePlan) throws Exception {
        CoursePlan result = coursePlanService.createCoursePlan(coursePlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Course Plan", result));
    }

    @GetMapping
    public ResponseEntity getAllCoursePlan(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "coursePlanId") String orderBy
    ) {
        try {
            Page<CoursePlan> coursePlans = coursePlanService.listCoursePlan(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get course plan list", coursePlans));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{coursePlanId}")
    public ResponseEntity delete(@PathVariable Integer coursePlanId) throws Exception {
        coursePlanService.deleteById(coursePlanId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully delete course plan", "Success"));
    }
}

package com.projectsiakad.controller;

import com.projectsiakad.model.CoursePlanDetail;
import com.projectsiakad.model.response.ErrorResponse;
import com.projectsiakad.model.response.PagingResponse;
import com.projectsiakad.model.response.SuccessResponse;
import com.projectsiakad.service.ICoursePlanDetailService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course-plan-details")
@Validated
public class CoursePlanDetailController {

    private ICoursePlanDetailService coursePlanDetailService;

    public CoursePlanDetailController(ICoursePlanDetailService coursePlanDetailService) {
        this.coursePlanDetailService = coursePlanDetailService;
    }

    @PostMapping
    public ResponseEntity createCoursePlanDetail(@Valid @RequestBody CoursePlanDetail coursePlanDetail) throws Exception {
        CoursePlanDetail result = coursePlanDetailService.createCoursePlanDetail(coursePlanDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Course Plan Detail", result));
    }

    @GetMapping
    public ResponseEntity getAllCoursePlanDetail(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "coursePlanDetailId") String orderBy
    ) {
        try {
            Page<CoursePlanDetail> coursePlanDetails = coursePlanDetailService.listCoursePlanDetail(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get course plan detail list", coursePlanDetails));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{coursePlanDetailId}")
    public ResponseEntity delete(@PathVariable Integer coursePlanDetailId) throws Exception {
        coursePlanDetailService.deleteById(coursePlanDetailId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully delete course plan detail", "Success"));
    }
}

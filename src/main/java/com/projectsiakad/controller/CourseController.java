package com.projectsiakad.controller;

import com.projectsiakad.model.Course;
import com.projectsiakad.model.response.ErrorResponse;
import com.projectsiakad.model.response.PagingResponse;
import com.projectsiakad.model.response.SuccessResponse;
import com.projectsiakad.service.ICourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {

    private ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody Course course) throws Exception {
        Course result = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Course", result));
    }

    @GetMapping
    public ResponseEntity getAllCourse(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "courseCode") String orderBy
    ) {
        try {
            Page<Course> courses = courseService.listCourse(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get course list", courses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{courseCode}")
    public ResponseEntity delete(@PathVariable String courseCode) throws Exception {
        courseService.deleteById(courseCode);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully delete course", "Success"));
    }

    @PutMapping("/{courseCode}")
    public ResponseEntity update(@PathVariable String courseCode, @RequestBody Course course) throws Exception {
        Course result = courseService.updateById(courseCode, course);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update course", result));

    }
}

package com.projectsiakad.controller;

import com.projectsiakad.model.Student;
import com.projectsiakad.model.response.ErrorResponse;
import com.projectsiakad.model.response.PagingResponse;
import com.projectsiakad.model.response.SuccessResponse;
import com.projectsiakad.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody Student student) throws Exception {
        Student result = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Student", result));
    }

    @GetMapping
    public ResponseEntity getAllStudent(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "npm") String orderBy
    ) {
        try {
            Page<Student> students = studentService.listStudent(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get student list", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{npm}")
    public ResponseEntity delete(@PathVariable Integer npm) throws Exception {
        studentService.deleteById(npm);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully delete student", "Success"));
    }

    @PutMapping("/{npm}")
    public ResponseEntity update(@PathVariable Integer npm, @RequestBody Student student) throws Exception {
        Student result = studentService.updateById(npm, student);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update student", result));

    }
}

package com.projectsiakad.controller;

import com.projectsiakad.model.Major;
import com.projectsiakad.model.response.ErrorResponse;
import com.projectsiakad.model.response.PagingResponse;
import com.projectsiakad.model.response.SuccessResponse;
import com.projectsiakad.service.IMajorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/majors")
@Validated
public class MajorController {

    private IMajorService majorService;

    public MajorController(IMajorService majorService) {
        this.majorService = majorService;
    }

    @PostMapping
    public ResponseEntity createMajor(@Valid @RequestBody Major major) throws Exception {
        Major result = majorService.createMajor(major);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Major", result));
    }

    @GetMapping
    public ResponseEntity getAllMajor(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "majorCode") String orderBy
    ) {
        try {
            Page<Major> majors = majorService.listMajor(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get major list", majors));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{majorCode}")
    public ResponseEntity delete(@PathVariable String majorCode) throws Exception {
        majorService.deleteById(majorCode);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully delete major", "Success"));
    }

    @PutMapping("/{majorCode}")
    public ResponseEntity update(@PathVariable String majorCode, @RequestBody Major major) throws Exception {
        Major result = majorService.updateById(majorCode, major);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update major", result));

    }
}
package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.service.SchoolYearService;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("AdminApiSchoolYearController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiSchoolYearController {
    @Autowired
    private WebAppValidator schoolYearValidator;

    @Autowired
    private SchoolYearService schoolYearService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(schoolYearValidator);
    }

    @GetMapping(path = "/school-years/{schoolYearId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SchoolYear> loadSchoolYear(@PathVariable(value = "schoolYearId") int schoolYearId) {
        try {
            SchoolYear schoolYear = this.schoolYearService.getSchoolYearById(schoolYearId);
            return new ResponseEntity<>(schoolYear, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/school-years", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addDepartment(@Valid @RequestBody SchoolYear schoolYear, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.schoolYearService.addSchoolYear(schoolYear)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/school-years/{schoolYearId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateSchoolYear(@PathVariable("schoolYearId") int schoolYearId, @Valid @RequestBody SchoolYear schoolYear, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.schoolYearService.updateSchoolYear(schoolYearId, schoolYear)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/school-years/{schoolYearId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSchoolYear(@PathVariable("schoolYearId") int schoolYearId) {
        this.schoolYearService.deleteSchoolYear(schoolYearId);
    }
}

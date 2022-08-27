package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController("AdminApiDepartmentController")
@Validated
@RequestMapping("/admin/api")
public class ApiDepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private WebAppValidator departmentValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(departmentValidator);
    }

    @GetMapping(path = "/departments/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Department> loadDepartment(@PathVariable(value = "departmentId") int departmentId) {
        try {
            Department department = this.departmentService.getDepartmentById(departmentId);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/departments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addDepartment(@Valid @RequestBody Department department,
                                                             BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.departmentService.addDepartment(department)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }


    @PatchMapping(path = "/departments/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateDepartment(@PathVariable("departmentId") int departmentId, @Valid @RequestBody Department department, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.departmentService.updateDepartment(departmentId, department)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/departments/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable("departmentId") int departmentId) {
        this.departmentService.deleteDepartment(departmentId);
    }
}

package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.response.admin.DepartmentResponse;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController("AdminApiDepartmentController")
@RequestMapping("/api")
public class ApiDepartmentController {
    @Autowired
    private WebAppValidator departmentValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(departmentValidator);
    }

    @PostMapping(path = "/departments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid Department department, BindingResult result) {
        DepartmentResponse response = new DepartmentResponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setValidated(true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

}

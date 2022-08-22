package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.service.MajorService;
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

@RestController(value = "AdminApiMajorController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiMajorController {
    @Autowired
    private MajorService majorService;
    @Autowired
    private WebAppValidator majorValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(majorValidator);
    }

    @GetMapping(path = "/majors/{majorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Major> loadMajor(@PathVariable(value = "majorId") int majorId) {
        try {
            Major major = this.majorService.getMajorById(majorId);
            return new ResponseEntity<>(major, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/majors", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addDepartment(@Valid @RequestBody Major major, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.majorService.addMajor(major)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/majors/{majorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateMajor(@PathVariable("majorId") int majorId, @Valid @RequestBody Major major, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.majorService.updateMajor(majorId, major)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/majors/{majorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMajor(@PathVariable("majorId") int majorId) {
        this.majorService.deleteMajor(majorId);
    }
}

package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.service.StudentService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "AdminApiStudentController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiStudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebAppValidator studentValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(studentValidator);
    }


    @GetMapping(path = "/student-options", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Object[]>> loadStudentOptions() {
        try {
            return new ResponseEntity<>(this.studentService.getStudentOptions(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/students/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> loadStudent(@PathVariable(value = "studentId") int studentId) {
        try {
            Student student = this.studentService.getStudentById(studentId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/students",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addStudent(@RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                          @RequestPart("student") @Valid  Student student,
                                                          BindingResult result) throws IOException {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.studentService.addStudent(student, file))
                status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PostMapping(path = "/students/{studentId}",
            consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("studentId") int studentId,
                                                             @RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                             @RequestPart("student") @Valid  Student student,
                                                             BindingResult result) throws IOException {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.studentService.updateStudent(studentId, student, file)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/students/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        this.studentService.deleteStudent(studentId);
    }
}

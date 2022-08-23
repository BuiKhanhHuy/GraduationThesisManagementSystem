package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.service.LecturerService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "AdminApiLecturerController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiLecturerController {
    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private WebAppValidator lecturerValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(lecturerValidator);
    }

    @GetMapping(path = "/lecturer-options", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Object[]>> loadLecturerOptions() {
        try {
            return new ResponseEntity<>(this.lecturerService.getLecturerOptions(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/lecturers/{lecturerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Lecturer> loadLecturer(@PathVariable(value = "lecturerId") int lecturerId) {
        try {
            Lecturer lecturer = this.lecturerService.getLecturerById(lecturerId);
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/lecturers",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addLecturer(@RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                           @RequestPart("lecturer") @Valid Lecturer lecturer, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.lecturerService.addLecturer(lecturer, file)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PostMapping(path = "/lecturers/{lecturerId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateLecturer(@PathVariable("lecturerId") int lecturerId,
                                                              @RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                              @RequestPart(value = "lecturer") @Valid Lecturer lecturer,
                                                              BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.lecturerService.updateLecturer(lecturerId, lecturer, file)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/lecturers/{lecturerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteManage(@PathVariable("lecturerId") int lecturerId) {
        this.lecturerService.deleteLecturer(lecturerId);
    }
}

package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.service.ThesisService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "AdminApiThesisController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiThesisController {
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private WebAppValidator thesisValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(thesisValidator);
    }

    @GetMapping(path = "/thesis-options", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Object[]>> loadThesisOptions(@RequestParam(value = "isCouncil", required = false) String isCouncil) {
        try {
            return new ResponseEntity<>(this.thesisService.getThesisOptions(isCouncil), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/theses/{thesisId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Thesis> loadThesis(@PathVariable(value = "thesisId") int thesisId) {
        try {
            return new ResponseEntity<>(this.thesisService.getThesisById(thesisId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(path = "/theses", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addThesis(@Valid @RequestBody Thesis thesis, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.thesisService.addThesis(thesis)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/theses/{thesisId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateThesis(@PathVariable("thesisId") int thesisId, @Valid @RequestBody Thesis thesis, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
//            if (this.departmentService.updateDepartment(departmentId, department))
//                status = HttpStatus.OK;
//            else status = HttpStatus.INTERNAL_SERVER_ERROR;

            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/theses/{thesisId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteThesis(@PathVariable("thesisId") int thesisId) {
        this.thesisService.deleteThesis(thesisId);
    }
}

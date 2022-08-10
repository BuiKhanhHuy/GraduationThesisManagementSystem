package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.service.CouncilService;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("AdminApiCouncilController")
@Validated
@RequestMapping("/admin/api")
public class ApiCouncilController {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private CouncilService councilService;
    @Autowired
    private WebAppValidator councilValidator;
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(councilValidator);
    }

    @GetMapping(path = "/councils/{councilId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Council> loadCouncil(@PathVariable(value = "councilId") int councilId) {
        try {
            Council council = sessionFactoryBean.getObject().openSession().get(Council.class, 1);
            return new ResponseEntity<>(council, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/councils", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addCouncil(@Valid @RequestBody Council council, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.councilService.addCouncil(council)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/councils/{councilId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCouncil(@PathVariable("councilId") int councilId) {
        this.councilService.deleteCouncil(councilId);
    }
}

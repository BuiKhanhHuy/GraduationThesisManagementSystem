package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.pojo.Topic;
import com.buikhanhhuy.service.ManageService;
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
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "AdminApiManageController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiManageController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private WebAppValidator manageValidator;
    @Autowired
    private WebAppValidator userValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(manageValidator);
    }

    @GetMapping(path = "/manages/{manageId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Manage> loadManage(@PathVariable(value = "manageId") int manageId) {
        try {
            Manage manage = this.manageService.getManageById(manageId);
            return new ResponseEntity<>(manage, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/manages",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addManage(@RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                         @RequestPart("manage") @Valid Manage manage, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.manageService.addManage(manage, file)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PostMapping(path = "/manages/{manageId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateManage(@PathVariable("manageId") int manageId,
                                                            @RequestPart(value = "avatarFile", required = false) MultipartFile file,
                                                            @Valid @RequestPart("manage") Manage manage,
                                                            BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.manageService.updateManage(manageId, manage, file)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/manages/{manageId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteManage(@PathVariable("manageId") int manageId) {
        this.manageService.deleteManage(manageId);
    }
}

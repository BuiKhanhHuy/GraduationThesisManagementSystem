package com.buikhanhhuy.api.user;

import com.buikhanhhuy.req.PasswordUser;
import com.buikhanhhuy.service.UserService;
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

@RestController(value = "UserApiUserController")
@RequestMapping(path = "/api")
@Validated
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WebAppValidator passwordUserValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(passwordUserValidator);
    }

    @PostMapping(path = "/users/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> changePasswordUser(@Valid @RequestBody PasswordUser passwordUser, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.userService.changePassword(passwordUser)) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(errorMessages, status);
    }
}

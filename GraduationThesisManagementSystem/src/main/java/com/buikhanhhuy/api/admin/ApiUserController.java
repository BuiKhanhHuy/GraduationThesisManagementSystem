package com.buikhanhhuy.api.admin;

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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "AdminApiUserController")
@RequestMapping(path = "/admin/api")
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

    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Object[]>> getUsers(@RequestParam Map<String, String> params) {

        List<Object[]> users = this.userService.getUsers(params);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public void changePassword(@PathVariable("userId") int userId, @RequestBody String newPassword) {
        this.userService.changePassword(userId, newPassword);
    }

    @PostMapping(path = "/users/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> changePasswordUser(@Valid @RequestBody PasswordUser passwordUser, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

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

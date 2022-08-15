package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "AdminApiUserController")
@RequestMapping(path = "/admin/api")
@Validated
public class ApiUserController {
    @Autowired
    private UserService userService;

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

}

package com.buikhanhhuy.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "UserApiHomeController")
@RequestMapping(path = "/api")
public class ApiHomeController {

    @GetMapping(path = "/lang")
    @ResponseStatus(HttpStatus.OK)
    public void changeLang() {

    }
}

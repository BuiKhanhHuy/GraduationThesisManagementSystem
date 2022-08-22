package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.service.PositionService;
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

@RestController(value = "AdminApiPositionController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiPositionController {
    @Autowired
    private PositionService positionService;
    @Autowired
    private WebAppValidator positionValidator;
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(positionValidator);
    }


    @GetMapping(path = "/positions/{positionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Position> loadPosition(@PathVariable(value = "positionId") int positionId) {
        try {
            Position position = this.positionService.getPositionById(positionId);
            return new ResponseEntity<>(position, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/positions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addPosition(@Valid @RequestBody Position position, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.positionService.addPosition(position)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/positions/{positionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updatePosition(@PathVariable("positionId") int positionId, @Valid @RequestBody Position position, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.positionService.updatePosition(positionId, position)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/positions/{positionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePosition(@PathVariable("positionId") int positionId) {
        this.positionService.deletePosition(positionId);
    }
}

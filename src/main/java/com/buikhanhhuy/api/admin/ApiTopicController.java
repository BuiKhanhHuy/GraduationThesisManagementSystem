package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Topic;
import com.buikhanhhuy.service.TopicService;
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

@RestController(value = "AdminApiTopicController")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiTopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private WebAppValidator topicValidator;


    @GetMapping(path = "/topics/{topicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Topic> loadTopic(@PathVariable(value = "topicId") int topicId) {
        try {
            Topic topic = this.topicService.getTopicById(topicId);
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/topics/{topicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateTopic(@PathVariable("topicId") int topicId, @Valid @RequestBody Topic topic, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.topicService.updateTopic(topicId, topic)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(topicValidator);
    }
    @PostMapping(path = "/topics", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addTopic(@Valid @RequestBody Topic topic,
                                                        BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.topicService.addTopic(topic)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/topics/{topicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        this.topicService.deleteTopic(topicId);
    }
}

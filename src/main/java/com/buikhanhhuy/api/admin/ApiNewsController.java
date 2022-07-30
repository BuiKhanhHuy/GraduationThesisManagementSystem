package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.News;
import com.buikhanhhuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("AdminApiNewsController")
@Validated
@RequestMapping("/admin/api")
public class ApiNewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping(path = "/news/{newsId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<News> loadNewsWithAuthor(@PathVariable(value = "newsId") int newsId) {
        try {
            News news = this.newsService.getNewsWithAuthorById(newsId);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/news/{newsId}/single", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object[]> loadNews(@PathVariable(value = "newsId") int newsId) {
        try {
            Object[] news = this.newsService.getNewsById(newsId);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/news", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addNews(@Valid @RequestBody News news, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.newsService.addNews(news)) status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/news/{newsId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateNews(@PathVariable("newsId") int newsId, @Valid @RequestBody News news, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.newsService.updateNews(newsId, news)) status = HttpStatus.OK;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }
    @DeleteMapping(path = "/news/{newsId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("newsId") int newsId) {
        this.newsService.deleteNews(newsId);
    }
}

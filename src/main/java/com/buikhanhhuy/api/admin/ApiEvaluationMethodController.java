package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.service.EvaluationMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.Utils;

import javax.validation.Valid;
import javax.ws.rs.HttpMethod;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("AdminApiEvaluationMethodController")
@Validated
@RequestMapping("/admin/api")
public class ApiEvaluationMethodController {
    @Autowired
    private EvaluationMethodService evaluationMethodService;

    @GetMapping(path = "/evaluations-method/{evaluationMethodId}", produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<EvaluationMethod> loadEvaluationMethod(@PathVariable(value = "evaluationMethodId") int evaluationMethodId) {
        try {
            EvaluationMethod evaluationMethod = this.evaluationMethodService.getEvaluationMethodById(evaluationMethodId);
            return new ResponseEntity<>(evaluationMethod, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/evaluations-method-active", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EvaluationMethod> loadEvaluationMethod() {
        try {
            EvaluationMethod evaluationMethod = this.evaluationMethodService.getEvaluationMethodActive();
            return new ResponseEntity<>(evaluationMethod, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/evaluations-method", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addEvaluationMethod(@Valid @RequestBody EvaluationMethod evaluationMethod,
                                                                   BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (Utils.checkTotalWeight(evaluationMethod)) {
                if (this.evaluationMethodService.addEvaluationMethod(evaluationMethod)) status = HttpStatus.CREATED;
                else status = HttpStatus.INTERNAL_SERVER_ERROR;

            } else {
                status = HttpStatus.BAD_REQUEST;
                errorMessages.put("errorTotalWeight", "Tổng trọng số của các cột điểm phải bằng 1 ~ 100%");
            }
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/evaluations-method/{evaluationMethodId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateEvaluationMethod(@PathVariable("evaluationMethodId") int evaluationMethodId,
                                                                      @Valid @RequestBody EvaluationMethod evaluationMethod, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (Utils.checkTotalWeight(evaluationMethod)) {
                if (this.evaluationMethodService.updateEvaluationMethod(evaluationMethodId, evaluationMethod))
                    status = HttpStatus.OK;
                else
                    status = HttpStatus.INTERNAL_SERVER_ERROR;

            } else {
                status = HttpStatus.BAD_REQUEST;
                errorMessages.put("errorTotalWeight", "Tổng trọng số của các cột điểm phải bằng 1 ~ 100%");
            }
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/evaluations-method/{evaluationMethodId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEvaluationMethod(@PathVariable("evaluationMethodId") int evaluationMethodId) {
        this.evaluationMethodService.deleteEvaluationMethod(evaluationMethodId);
    }

    @PostMapping(path = "/evaluations-method/{evaluationMethodId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public void activeEvaluationMethod(@PathVariable("evaluationMethodId") int evaluationMethodId) {
        this.evaluationMethodService.activeAEvaluationMethod(evaluationMethodId);
    }
}

package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Score;
import com.buikhanhhuy.service.CouncilDetailService;
import com.buikhanhhuy.service.ScoreService;
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

@RestController(value = "Admin@RestController(value = \"AdminApiStudentController\")\n")
@Validated
@RequestMapping(path = "/admin/api")
public class ApiScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CouncilDetailService councilDetailService;

    @GetMapping(path = "/score", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Score> getScoreByThesisIdAndCouncilDetailId(@RequestParam Map<String, String> params) {
        HttpStatus status = null;
        Score score = null;

        if (params != null) {
            if (!params.containsKey("thesisId") || !params.containsKey("councilDetailId")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                if (params.get("thesisId") == null || params.get("councilDetailId") == null) {
                    status = HttpStatus.BAD_REQUEST;
                } else {
                    score = this.scoreService.getScoreWithThesisIdAndCouncilDetailId(Integer.parseInt(params.get("thesisId")), Integer.parseInt(params.get("councilDetailId")));

                    status = HttpStatus.OK;
                }
            }
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(score, status);
    }

    @PostMapping(path = "/scores", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addScore(@Valid @RequestBody Score score, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        int councilDetailId = score.getCouncilDetail().getId();

        if (this.councilDetailService.checkAllowAddAndEdit(councilDetailId) == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (this.councilDetailService.checkAllowAddAndEdit(councilDetailId)) {
            status = HttpStatus.FORBIDDEN;
        } else {
            if (result.hasErrors()) {
                errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
                status = HttpStatus.BAD_REQUEST;
            } else {
                if (this.scoreService.addScore(score)) status = HttpStatus.CREATED;
                else status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        }
        return new ResponseEntity<>(errorMessages, status);
    }

    @PatchMapping(path = "/scores/{scoreId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateScore(@PathVariable("scoreId") int scoreId, @Valid @RequestBody Score score, BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        int councilDetailId = score.getCouncilDetail().getId();

        if (this.councilDetailService.checkAllowAddAndEdit(councilDetailId) == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (this.councilDetailService.checkAllowAddAndEdit(councilDetailId)) {
            status = HttpStatus.FORBIDDEN;
        } else {
            if (result.hasErrors()) {
                errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
                status = HttpStatus.BAD_REQUEST;
            } else {
                if (this.scoreService.updateScore(scoreId, score)) status = HttpStatus.OK;
                else status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(errorMessages, status);
    }
}

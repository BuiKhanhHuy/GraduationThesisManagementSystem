package com.buikhanhhuy.api.user;

import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController(value = "UserApiThesisController")
@RequestMapping(path = "/api")
public class ApiThesisController {
    @Autowired
    private ThesisService thesisService;

    @PostMapping(path = "/theses/{thesisId}/report-file",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void uploadReportFile(@PathVariable(value = "thesisId") int thesisId,
                                 @RequestParam(value = "reportFile", required = false) MultipartFile reportFile,
                                 @RequestPart(value = "fileName") String fileName) {

        this.thesisService.uploadThesisReportFile(thesisId, reportFile, fileName);
    }
}

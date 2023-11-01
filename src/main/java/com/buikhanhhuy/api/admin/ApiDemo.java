package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.service.ReadExcelService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@RestController("ApiDemo")
@Validated
@RequestMapping("/api")
public class ApiDemo {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ReadExcelService readExcelService;

    //demo
    @GetMapping(path = "/demo")
    public String demo(Model model) throws IOException {
        Map res = cloudinary.uploader().upload("https://cdn.tgdd.vn/Products/Images/42/230529/TimerThumb/iphone-13-pro-max-(8).jpg", ObjectUtils.asMap("resource_type", "auto"));
        System.out.println(res.get("secure_url"));
        return "index";
    }

    @PostMapping(path = "/upload-file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Student>> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        try {
            List<Student> students = this.readExcelService.getStudentsFromFile(file);

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}

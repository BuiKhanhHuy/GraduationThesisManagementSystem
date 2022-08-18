package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.ThesisRepository;
import com.buikhanhhuy.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController("ApiDemo")
@Validated
@RequestMapping("/api")
public class ApiDemo {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/upload-file",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestPart("user") User user) throws IOException {
//        Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
//
        System.out.println(user);
        System.out.println("OKE");
    }

    @GetMapping(path = "/tinh-diem")
    @ResponseStatus(HttpStatus.OK)
    public  void tinhDiem(){
//        thesisRepository.scoreOfAThesisInCouncil(47, 30);
        System.out.println(userService.checkPassword(6, "123456789"));
    }
}

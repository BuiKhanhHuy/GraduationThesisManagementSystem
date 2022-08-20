package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
public class CloudinaryServiceImplement implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            Map res = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));

            return res.get("secure_url").toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            Map res = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("public_id", fileName, "resource_type", "raw",  "format", "zip"));

            return res.get("secure_url").toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

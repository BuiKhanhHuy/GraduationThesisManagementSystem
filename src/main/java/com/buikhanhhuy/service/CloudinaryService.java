package com.buikhanhhuy.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    public String uploadAvatar(MultipartFile file);
    public String uploadFile(MultipartFile file, String fileName);
}

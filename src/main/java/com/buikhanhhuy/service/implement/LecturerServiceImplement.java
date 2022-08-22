package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.repository.LecturerRepository;
import com.buikhanhhuy.service.CloudinaryService;
import com.buikhanhhuy.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class LecturerServiceImplement implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public boolean checkUniqueLecturerCode(String lecturerCode) {
        return this.lecturerRepository.checkUniqueLecturerCode(lecturerCode);
    }

    @Override
    public boolean checkUniqueLecturerEmail(String lecturerEmail) {
        return this.lecturerRepository.checkUniqueLecturerEmail(lecturerEmail);
    }

    @Override
    public boolean checkUniqueLecturerPhone(String lecturerPhone) {
        return this.lecturerRepository.checkUniqueLecturerPhone(lecturerPhone);
    }

    @Override
    public List<Object[]> getLecturerOptions() {
        return this.lecturerRepository.getLecturerOptions();
    }

    @Override
    public List<Lecturer> getLecturers(Map<String, String> params) {
        return this.lecturerRepository.getLecturers(params);
    }

    @Override
    public long countLecturer(Map<String, String> params) {
        return this.lecturerRepository.countLecturer(params);
    }

    @Override
    public long countAllLecturer() {
        return this.lecturerRepository.countAllLecturer();
    }

    @Override
    public Lecturer getLecturerById(int lecturerId) {
        return this.lecturerRepository.getLecturerById(lecturerId);
    }

    @Override
    public boolean addLecturer(Lecturer lecturer, MultipartFile file) {
       if(file != null){
           String avatarStr = this.cloudinaryService.uploadAvatar(file);
           lecturer.getUser().setAvatar(avatarStr);
       }

        return this.lecturerRepository.addLecturer(lecturer);
    }

    @Override
    public boolean updateLecturer(int lecturerId, Lecturer lecturer, MultipartFile file) {
        if(file != null){
            String avatarStr = this.cloudinaryService.uploadAvatar(file);
            lecturer.getUser().setAvatar(avatarStr);
        }

        return this.lecturerRepository.updateLecturer(lecturerId, lecturer);
    }

    @Override
    public boolean deleteLecturer(int lecturerId) {
        return this.lecturerRepository.deleteLecturer(lecturerId);
    }
}

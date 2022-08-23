package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.NotificationRepository;
import com.buikhanhhuy.repository.ThesisRepository;
import com.buikhanhhuy.service.CloudinaryService;
import com.buikhanhhuy.service.EmailService;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ThesisServiceImplement implements ThesisService {
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void sendReviewLectureThesisNotification(Thesis thesis) {
        String[] toEmail;
        Map<String, Object> model = new HashMap<>();
        Thesis modelThesis = this.thesisRepository.getThesisById(thesis.getId());

        model.put("thesis", modelThesis);

        toEmail = new String[]{modelThesis.getReviewLecturer().getEmail()};

        this.emailService.sendMail("Thông báo giảng viên phản biện khóa luận tốt nghiệp",
                toEmail, model,
                SystemConstant.REVIEW_LECTURER_EMAIL_TEMPLATE);
    }

    @Override
    public boolean checkStudentDoManyThesisInASchoolYear(int studentId) {
        return this.thesisRepository.checkStudentDoManyThesisInASchoolYear(studentId);
    }

    @Override
    public boolean checkStudentCompletedThesis(int studentId) {
        return this.thesisRepository.checkStudentCompletedThesis(studentId);
    }

    @Override
    public List<Object[]> getThesisOptions(String isCouncil) {
        return this.thesisRepository.getThesisOptions(isCouncil);
    }

    @Override
    public List<Thesis> getTheses(Map<String, String> params) {
        return this.thesisRepository.getTheses(params);
    }


    @Override
    public long countThesis(Map<String, String> params) {
        return this.thesisRepository.countThesis(params);
    }

    @Override
    public long countAllThesis() {
        return this.thesisRepository.countAllThesis();
    }

    @Override
    public Boolean addThesis(Thesis thesis) {
        if (this.thesisRepository.addThesis(thesis)) {
            Thesis thesisResult = this.thesisRepository.getThesisById(thesis.getId());
            Set<Integer> usersId;
            Notification notification;

            usersId = new HashSet<>();
            notification = new Notification();
            for (Student student : thesisResult.getStudents()) {
                usersId.add(student.getUser().getId());
            }
            notification.setTitle("Thông báo thực hiện khóa luận tốt nghiệp");
            notification.setContent(String.format("Thông báo thực hiện khóa luận từ ngày %s đến ngày %s. " +
                            "Thông tin chi tiết trên hệ thống.",
                    thesisResult.getStartDate().toString(),
                    thesisResult.getComplateDate().toString()));
            this.notificationRepository.addNotification(notification, usersId);

            usersId = new HashSet<>();
            notification = new Notification();
            for (Lecturer lecturer : thesisResult.getLecturers()) {
                usersId.add(lecturer.getUser().getId());
            }
            notification.setTitle("Thông báo giảng viên hướng dẫn khóa luận");
            notification.setContent("Đăng nhập vào hệ thống để xem thông tin chi tiết");
            this.notificationRepository.addNotification(notification, usersId);

            usersId = new HashSet<>();
            notification = new Notification();
            usersId.add(thesisResult.getReviewLecturer().getUser().getId());
            notification.setTitle("Thông báo giảng viên phản biện khóa luận tốt nghiệp");
            notification.setContent("Đăng nhập vào hệ thống để xem thông tin chi tiết");
            this.notificationRepository.addNotification(notification, usersId);

            this.sendReviewLectureThesisNotification(thesisResult);
            return true;
        }

        return false;
    }

    @Override
    public Thesis getThesisById(int thesisId) {
        return this.thesisRepository.getThesisById(thesisId);
    }

    @Override
    public boolean deleteThesis(int thesisId) {
        return this.thesisRepository.deleteThesis(thesisId);
    }

    @Override
    public boolean uploadThesisReportFile(int thesisId, MultipartFile reportFile, String fileName) {
        if (reportFile != null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fileName += "_" + timestamp.getTime();

            String urlReportFile = this.cloudinaryService.uploadFile(reportFile, fileName);

            return this.thesisRepository.uploadThesisReportFile(thesisId, urlReportFile);
        }

        return false;
    }


}

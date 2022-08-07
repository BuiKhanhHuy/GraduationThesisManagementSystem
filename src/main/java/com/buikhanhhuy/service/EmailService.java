package com.buikhanhhuy.service;

import java.util.Map;

public interface EmailService {
    public void sendMail(String subject, String [] to, Map<String, String> model, int type);
}

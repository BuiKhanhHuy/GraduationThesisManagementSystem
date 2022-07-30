package com.buikhanhhuy.service;


import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Object[]> getUsers(Map<String, String> params);
}

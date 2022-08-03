package com.buikhanhhuy.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    public List<Object[]> getUsers(Map<String, String> params);
    public Set<Integer> getUsers(Map<String, String> params, List<Integer> usersId);
}

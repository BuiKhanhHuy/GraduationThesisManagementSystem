package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRepository {
    public List<Object[]> getUsers(Map<String, String> params);
    public Set<Integer> getUsers(Map<String, String> params, List<Integer> usersId);
    public boolean addUser(User user);
    public User getUserByUserName(String username);
}

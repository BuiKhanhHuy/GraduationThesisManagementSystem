package com.buikhanhhuy.service;


import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.req.PasswordUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService extends UserDetailsService {
    public boolean checkUniqueUserUsername(String username);
    public List<Object[]> getUsers(Map<String, String> params);
    public Set<Integer> getUsers(Map<String, String> params, List<Integer> usersId);
    public User getUserByUserName(String username);
    public void changePassword(int userId, String newPassword);
    public boolean changePassword(PasswordUser passwordUser);
    public boolean checkPassword(int userId, String password);
}

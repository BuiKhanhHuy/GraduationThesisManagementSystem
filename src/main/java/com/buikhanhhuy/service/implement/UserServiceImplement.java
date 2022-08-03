package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.repository.UserRepository;
import com.buikhanhhuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Object[]> getUsers(Map<String, String> params) {
        return this.userRepository.getUsers(params);
    }

    @Override
    public Set<Integer> getUsers(Map<String, String> params, List<Integer> usersId) {
        return this.userRepository.getUsers(params, usersId);
    }
}

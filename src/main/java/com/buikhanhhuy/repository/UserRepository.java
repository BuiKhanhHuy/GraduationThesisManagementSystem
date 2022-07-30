package com.buikhanhhuy.repository;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    public List<Object[]> getUsers(Map<String, String> params);
}

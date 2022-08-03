package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles(String kw);
    public List<Object[]> getRoleOptions(String[] exceptRole);
}

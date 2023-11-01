package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Role;

import java.util.List;

public interface RoleRepository {
    public List<Role> getRoles(String kw);

    public List<Object[]> getRoleOptions(String[] exceptRole);

    public Role getRoleByRoleName(String roleName);
}

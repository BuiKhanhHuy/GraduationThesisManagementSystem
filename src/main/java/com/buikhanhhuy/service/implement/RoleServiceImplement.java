package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Role;
import com.buikhanhhuy.repository.RoleRepository;
import com.buikhanhhuy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles(String kw) {
        return this.roleRepository.getRoles(kw);
    }

    @Override
    public List<Object[]> getRoleOptions(String[] exceptRole) {
        return this.roleRepository.getRoleOptions(exceptRole);
    }
}

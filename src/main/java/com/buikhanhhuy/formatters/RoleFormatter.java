package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Role;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {
    @Override
    public Role parse(String roleId, Locale locale) throws ParseException {
        Role r = new Role();
        r.setId(Integer.parseInt(roleId));

        return r;
    }

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }
}

package com.buikhanhhuy.validators;

import com.buikhanhhuy.req.PasswordUser;
import com.buikhanhhuy.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {
    private final UserService userService;

    public PasswordValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordUser passwordUser = (PasswordUser) target;

        if (!passwordUser.getOldPassword().isEmpty()) {
            if (!this.userService.checkPassword(passwordUser.getUserId(), passwordUser.getOldPassword())) {
                errors.rejectValue("oldPassword",
                        "passwordUser.password.invalidOldPasswordMessage",
                        "Mật khẩu cũ không khớp!");
            }
        }
    }
}

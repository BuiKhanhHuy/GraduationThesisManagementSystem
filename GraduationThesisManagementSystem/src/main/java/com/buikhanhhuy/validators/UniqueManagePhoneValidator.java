package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.service.ManageService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueManagePhoneValidator implements Validator {
    private final ManageService manageService;

    public UniqueManagePhoneValidator(ManageService manageService) {
        this.manageService = manageService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Manage manage = (Manage) target;

        if (manage.getId() == null
                && this.manageService.checkUniqueManagePhone(manage.getPhone())) {
            errors.rejectValue("phone", "manage.add.phone.existsMessage",
                    "Số điện thoại quản trị viên đã tồn tại");
        }
    }
}

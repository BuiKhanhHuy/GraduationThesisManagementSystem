package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.service.ManageService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueManageEmailValidator implements Validator {
    private final ManageService manageService;

    public UniqueManageEmailValidator(ManageService manageService){
        this.manageService = manageService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Manage manage = (Manage) target;

        if(manage.getId() == null
                && this.manageService.checkUniqueManageEmail(manage.getEmail())){
            errors.rejectValue("email", "manage.add.email.existsMessage",
                    "Email quản trị viên đã tồn tại");
        }
    }
}

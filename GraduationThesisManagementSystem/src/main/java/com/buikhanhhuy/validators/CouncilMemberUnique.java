package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.CouncilDetail;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public class CouncilMemberUnique implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Council.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        Council council = (Council) target;
//        Set<CouncilDetail> councilDetailSet = new HashSet<>(council.getCouncilDetails());
//
//        if (council.getCouncilDetails().size() > councilDetailSet.size()) {
//            errors.rejectValue("councilDetails",
//                    "council.add.councilDetails.uniqueMessage",
//                    "Các thành viên trong 1 hội đồng không được trùng nhau");
//        }
    }
}

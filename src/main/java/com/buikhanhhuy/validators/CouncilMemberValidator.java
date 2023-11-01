package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Council;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CouncilMemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Council.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Council council = (Council) target;

        if (council.getCouncilDetails().size() < SystemConstant.MIN_NUMBER_MEMBER_OF_COUNCIL) {
            errors.rejectValue("councilDetails", "council.add.councilDetails.minQuantityMessage", "Số lượng thành viên trong hội đồng không thấp hơn 3");
        }

        if (council.getCouncilDetails().size() > SystemConstant.MAX_NUMBER_MEMBER_OF_COUNCIL) {
            errors.rejectValue("councilDetails",  "council.add.councilDetails.maxQuantityMessage", "Số lượng thành viên trong hội đồng không lớn hơn 5");
        }
    }
}

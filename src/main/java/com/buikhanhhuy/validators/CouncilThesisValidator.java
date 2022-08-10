package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Council;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CouncilThesisValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Council.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Council council = (Council) target;

        if (council.getTheses().size() > SystemConstant.MAX_THESIS_OF_COUNCIL) {
            errors.rejectValue("theses",
                    "council.add.theses.maxQuantityMessage",
                    "1 hội đồng chỉ được chấm điểm tối đa cho 5 khóa luận");
        }
    }
}

package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.service.PositionService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniquePositionNameValidator implements Validator {
    private final PositionService positionService;

    public UniquePositionNameValidator(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Position.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Position position = (Position) target;

        if (position.getId() == null
                && this.positionService.checkUniquePositionName(position.getName())) {
            errors.rejectValue("name", "position.add.name.existsMessage",
                    "Chức vụ đã tồn tại");
        }
    }
}

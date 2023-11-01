package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueDepartmentCodeValidator implements Validator {
    private final DepartmentService departmentService;

    public UniqueDepartmentCodeValidator(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        if(department.getId() == null
                && this.departmentService.checkUniqueDepartmentCode(department.getCode())){
            errors.rejectValue("code",
                    "department.add.code.existsMessage",
                    "Mã khoa đã tồn tại.");
        }
    }

}

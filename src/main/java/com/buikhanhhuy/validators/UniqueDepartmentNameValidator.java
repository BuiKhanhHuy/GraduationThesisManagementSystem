package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueDepartmentNameValidator implements Validator {
    private final DepartmentService departmentService;

    public UniqueDepartmentNameValidator(DepartmentService departmentService){
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
                && this.departmentService.checkUniqueDepartmentName(department.getName())){
            errors.rejectValue("name", "department.add.name.existsMessage",
                    "Tên khoa đã tồn tại");
        }
    }
}

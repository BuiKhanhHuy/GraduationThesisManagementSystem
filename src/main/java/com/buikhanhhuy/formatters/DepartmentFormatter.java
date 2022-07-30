package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Department;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class DepartmentFormatter implements Formatter<Department> {
    @Override
    public Department parse(String departmentId, Locale locale) throws ParseException {
        Department d = new Department();
        d.setId(Integer.parseInt(departmentId));
        return d;
    }

    @Override
    public String print(Department department, Locale locale) {
        return String.valueOf(department.getId());
    }
}

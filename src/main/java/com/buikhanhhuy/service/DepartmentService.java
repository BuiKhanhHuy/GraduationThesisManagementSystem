package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public List<Department> getDepartments();
    public boolean addDepartment(Department department);
}

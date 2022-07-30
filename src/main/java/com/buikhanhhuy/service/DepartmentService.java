package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public List<Department> getDepartments();
    public List<Object[]> getDepartmentOptions();
    public boolean addDepartment(Department department);
    public Department getDepartmentById(int departmentId);
    public boolean updateDepartment(int departmentId, Department department);
    public boolean deleteDepartment(int departmentId);
}

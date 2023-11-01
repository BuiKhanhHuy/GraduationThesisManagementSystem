package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {
    public boolean checkUniqueDepartmentCode(String departmentCode);
    public boolean checkUniqueDepartmentName(String departmentName);
    public List<Department> getDepartments(Map<String, String> params);
    public long countDepartment(Map<String, String> params);
    public List<Object[]> getDepartmentOptions();
    public boolean addDepartment(Department department);
    public Department getDepartmentById(int departmentId);
    public boolean updateDepartment(int departmentId, Department department);
    public boolean deleteDepartment(int departmentId);
}

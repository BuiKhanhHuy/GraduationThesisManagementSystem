package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Department;

import java.util.List;

public interface DepartmentRepository {
    public List<Department> getDepartments ();
    public List<Object[]> getDepartmentOptions();
    public boolean addDepartment(Department department);
    public boolean checkCodeExists(String code);
    public Department getDepartmentById(int departmentId);
    public boolean updateDepartment(int departmentId, Department department);
    public boolean deleteDepartment (int departmentId);
}

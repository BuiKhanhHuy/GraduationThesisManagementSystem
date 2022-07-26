package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Department;

import java.util.List;

public interface DepartmentRepository {
    public List<Department> getDepartments ();
    public boolean addDepartment(Department department);
}

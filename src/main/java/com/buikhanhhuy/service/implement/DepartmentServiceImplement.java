package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.repository.DepartmentRepository;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImplement implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartments() {
        return this.departmentRepository.getDepartments();
    }

    @Override
    public boolean addDepartment(Department department) {
        return this.departmentRepository.addDepartment(department);
    }
}

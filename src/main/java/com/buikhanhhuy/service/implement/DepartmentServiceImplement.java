package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.repository.DepartmentRepository;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImplement implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean checkUniqueDepartmentCode(String departmentCode) {
        return this.departmentRepository.checkUniqueDepartmentCode(departmentCode);
    }

    @Override
    public boolean checkUniqueDepartmentName(String departmentName) {
        return this.departmentRepository.checkUniqueDepartmentName(departmentName);
    }

    @Override
    public List<Department> getDepartments(Map<String, String> params) {
        return this.departmentRepository.getDepartments(params);
    }

    @Override
    public long countDepartment(Map<String, String> params) {
        return this.departmentRepository.countDepartment(params);
    }

    @Override
    public List<Object[]> getDepartmentOptions() {
        return this.departmentRepository.getDepartmentOptions();
    }

    @Override
    public boolean addDepartment(Department department) {
        return this.departmentRepository.addDepartment(department);
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        return this.departmentRepository.getDepartmentById(departmentId);
    }

    @Override
    public boolean updateDepartment(int departmentId, Department department) {
        return this.departmentRepository.updateDepartment(departmentId, department);
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        return this.departmentRepository.deleteDepartment(departmentId);
    }
}

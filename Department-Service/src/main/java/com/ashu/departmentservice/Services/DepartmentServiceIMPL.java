package com.ashu.departmentservice.Services;

import com.ashu.departmentservice.Entity.Department;
import com.ashu.departmentservice.Repositories.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DepartmentServiceIMPL implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;


    @Override
    public Department saveDepartment(Department department) {
        return this.departmentRepo.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return this.departmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Department not found !!!"));
    }
}

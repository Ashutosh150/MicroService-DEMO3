package com.ashu.departmentservice.Services;

import com.ashu.departmentservice.Entity.Department;

public interface DepartmentService {


    Department saveDepartment(Department department);

    Department getDepartmentById(Long id);

}

package com.ashu.departmentservice.Repositories;

import com.ashu.departmentservice.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}

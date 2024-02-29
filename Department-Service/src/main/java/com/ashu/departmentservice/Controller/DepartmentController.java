package com.ashu.departmentservice.Controller;

import com.ashu.departmentservice.Entity.Department;
import com.ashu.departmentservice.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    //Create
    @PostMapping("/save")
    public Department createDepartment(@RequestBody Department department){
      return this.departmentService.saveDepartment(department);
    }

    // fetching department by id
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id){
        return this.departmentService.getDepartmentById(id);
    }






}

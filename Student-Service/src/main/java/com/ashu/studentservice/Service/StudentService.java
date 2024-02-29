package com.ashu.studentservice.Service;

import com.ashu.studentservice.Entity.Student;

public interface StudentService {

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    String getStudentwithDepartment(Long id);
}

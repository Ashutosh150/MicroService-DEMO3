package com.ashu.studentservice.Service;

import com.ashu.studentservice.Entity.Department;
import com.ashu.studentservice.Entity.Student;
import com.ashu.studentservice.Exception.StudentNotFoundException;
import com.ashu.studentservice.Repositories.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class StudentServiceIMPL implements StudentService{

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    //for contacting department service
//    private String baseUrl = "http://localhost:8080/department/";
    private String baseUrl = "http://DEPARTMENTSERVICE/department/";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student saveStudent(Student student) {
        return this.studentRepo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return this.studentRepo.findById(id).get();
    }



    @Override
    public String getStudentwithDepartment(Long id) {
//        Student student = this.studentRepo.findById(id).get();
        Student student = this.studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        Department department = restTemplate.getForObject(baseUrl+student.getDepartmentId(), Department.class);

        StringBuffer buffer = new StringBuffer();
        buffer.append("\n");
        buffer.append(student.getStudentId()+" "+student.getFirstName()+" "+student.getLastName()+" "+student.getEmail()+" "+student.getDepartmentId());
        buffer.append("\n");
        buffer.append(department.getDepartmentName()+" "+department.getDepartmentAddress()+" "+department.getDepartmentCode());

        logger.info("Student with department Details: "+buffer.toString());

        return buffer.toString();
    }



}

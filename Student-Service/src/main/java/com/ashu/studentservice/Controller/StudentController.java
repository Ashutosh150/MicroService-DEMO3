package com.ashu.studentservice.Controller;

import com.ashu.studentservice.Entity.Student;
import com.ashu.studentservice.Service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")

public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;


    //create
    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student){
        return this.studentService.saveStudent(student);
    }


    //get StudentByID
    @GetMapping("/id/{id}")
    public Student getStudentById(@PathVariable Long id){
        return this.studentService.getStudentById(id);
    }

    // get Student with Department
    @GetMapping("/{id}")

    // resiience annaotation
    @CircuitBreaker(name = "studentservice", fallbackMethod = "fallbackMethod")

    // time-out & retry resilince4j annotations
    @Retry(name = "studentservice")
    @TimeLimiter(name = "studentservice")

    @ResponseStatus(HttpStatus.ACCEPTED)
    public CompletableFuture<String > getStudentWithDepartment(@PathVariable Long id){
        return CompletableFuture.supplyAsync(() -> this.studentService.getStudentwithDepartment(id));
    }

    //resilence4j custom fall-back-method for "getStudentWithDepartment" method
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CompletableFuture<String> fallbackMethod(@PathVariable Long id, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Service is down. Please try after some time.");
    }



}

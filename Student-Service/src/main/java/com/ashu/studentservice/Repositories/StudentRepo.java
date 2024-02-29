package com.ashu.studentservice.Repositories;

import com.ashu.studentservice.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}

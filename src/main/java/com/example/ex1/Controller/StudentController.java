package com.example.ex1.Controller;

import com.example.ex1.Model.Student;
import com.example.ex1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired UserRepo userRepo;
    private Student student = new Student(1,"ss",90);


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(){
        System.out.println( userRepo.findAll());
        return ResponseEntity.ok(student);
    }
}

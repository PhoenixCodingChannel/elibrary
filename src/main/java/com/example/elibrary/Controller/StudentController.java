package com.example.elibrary.Controller;

import com.example.elibrary.Service.StudentService;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.request.StudentCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody
                                               StudentCreateRequest studentCreateRequest){
        return new ResponseEntity<>(studentService.saveStudent(studentCreateRequest),
                HttpStatus.CREATED);
    }
}

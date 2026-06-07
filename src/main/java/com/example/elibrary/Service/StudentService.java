package com.example.elibrary.Service;

import com.example.elibrary.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student saveStudent(StudentCreateRequest studentCreateRequest) {

    }
}

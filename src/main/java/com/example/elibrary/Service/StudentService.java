package com.example.elibrary.Service;

import com.example.elibrary.Repository.StudentRepository;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student saveStudent
            (StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.toStudent();
        return studentRepository.save(student);
    }
}

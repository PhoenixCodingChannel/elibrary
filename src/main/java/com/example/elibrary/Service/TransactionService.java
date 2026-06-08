package com.example.elibrary.Service;

import com.example.elibrary.Repository.TransactionRepository;
import com.example.elibrary.exception.TransactionServiceException;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.Transaction;
import com.example.elibrary.models.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TransactionRepository transactionRepository;

    public String transact(int studentId, int bookId, String transactionType)
            throws TransactionServiceException {

        Optional<Student> student = studentService.getStudent(studentId);

        //validate Student
        if(student.isEmpty()){
            throw new TransactionServiceException("Student is not valid");
        }

        //validate book
        Optional<Book> bookById = bookService.findBookById(bookId);

        if(bookById.isEmpty()){
            throw new TransactionServiceException("Book is not valid");
        }

        //Check if book is available
        if(bookById.get().getStudent() !=null){
            throw new TransactionServiceException("Book is not available");
        }

        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment((double)bookById.get().getCost())
                .book(bookById.get())
                .student(student.get())
                .build();

        transactionRepository.save(transaction);

        return transaction.getExternalId();
    }
}

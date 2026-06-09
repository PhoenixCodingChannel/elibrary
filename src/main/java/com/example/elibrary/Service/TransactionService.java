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
import java.util.concurrent.TimeUnit;

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

        return switch (transactionType) {
            case "ISSUE" -> issueBookTransaction(student, bookById);
            case "RETURN" -> returnBookTransaction(student, bookById);
            default -> throw new TransactionServiceException("Transaction type not supported");
        };

    }



    private String issueBookTransaction(Optional<Student> student, Optional<Book> bookById)
            throws TransactionServiceException {
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

        //Make the book unavailable for others
        bookById.get().setStudent(student.get());
        bookService.save(bookById.get());
        return transaction.getExternalId();

    }
    private String returnBookTransaction(Optional<Student> student, Optional<Book> bookById)
            throws TransactionServiceException {
        //check if book is already issued
        if(bookById.get().getStudent() ==null){
            throw new TransactionServiceException("Book is not issued to any student");
        }

        //check if book is issued to the same student
        if(bookById.get().getStudent().getId() != student.get().getId()){
            throw new TransactionServiceException("Book is not issued to a different student");
        }

        Transaction issueTransaction = transactionRepository
                .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(bookById.get(),student.get(),TransactionType.ISSUE);
        //calculate fine
        calculateFine(issueTransaction);

        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .payment((double)bookById.get().getCost()-calculateFine(issueTransaction))
                .book(bookById.get())
                .student(student.get())
                .build();

        transactionRepository.save(transaction);
        return transaction.getExternalId();

    }

    private long calculateFine(Transaction issueTransaction) {

        long bookIssueTime = issueTransaction.getCreatedOn().getTime();
        long bookReturnTime = System.currentTimeMillis();

        long differenceInMillis = bookReturnTime - bookIssueTime;
        long daysPassed = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);

        if(daysPassed > 15){
            return (daysPassed-15)* 10L;
        }
        return 0;
    }


}

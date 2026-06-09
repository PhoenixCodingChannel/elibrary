package com.example.elibrary.Repository;

import com.example.elibrary.models.Book;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.Transaction;
import com.example.elibrary.models.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);
}

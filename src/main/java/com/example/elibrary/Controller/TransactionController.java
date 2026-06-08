package com.example.elibrary.Controller;

import com.example.elibrary.Service.TransactionService;
import com.example.elibrary.exception.TransactionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity transaction(@RequestParam("studentId") int studentId,
                                      @RequestParam("bookId") int bookId,
                                      @PathVariable("transactionType") String transactionType)
            throws TransactionServiceException {
        return  new ResponseEntity(transactionService.transact(studentId,bookId,transactionType), HttpStatus.CREATED);
    }
}

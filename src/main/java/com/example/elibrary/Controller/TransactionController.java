package com.example.elibrary.Controller;

import com.example.elibrary.Service.TransactionService;
import com.example.elibrary.exception.TransactionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{transactionType}")
    public ResponseEntity transaction(@RequestParam("studentId") int studentId,
                                      @RequestParam("bookId") int bookId,
                                      @PathVariable("transactionType") String transactionType)
            throws TransactionServiceException {
        return  new ResponseEntity(transactionService.transact(studentId,bookId,transactionType), HttpStatus.CREATED);
    }
}

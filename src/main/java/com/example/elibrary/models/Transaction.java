package com.example.elibrary.models;

import com.example.elibrary.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double payment;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Student student;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}

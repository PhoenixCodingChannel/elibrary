package com.example.elibrary.models;

import com.example.elibrary.models.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique =true)
    private String email;

    @Column(unique=true, nullable =false)
    private String phone;

    private String address;

    private AccountStatus accountStatus;

    @OneToMany(mappedBy="student")
    private List<Book> books;

    @OneToMany(mappedBy = "student")
    private List<Transaction> transactions;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}

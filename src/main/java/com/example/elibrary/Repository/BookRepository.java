package com.example.elibrary.Repository;

import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String name);
    List<Book> findByAuthorName(String author);
    List<Book> findByCost(int cost);
    List<Book> findByGenre(String genre);
}

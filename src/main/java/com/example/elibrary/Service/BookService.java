package com.example.elibrary.Service;

import com.example.elibrary.Repository.AuthorRepository;
import com.example.elibrary.Repository.BookRepository;
import com.example.elibrary.models.Author;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.BookFilterType;
import com.example.elibrary.models.request.BookCreateRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(@NonNull BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.toBook();
        Author author = book.getAuthor();

        //Check if author already exists
        Author authorFromDb = authorRepository.findByEmail(author.getEmail());
        if(authorFromDb == null){
            authorRepository.save(author);
        }
        book.setAuthor(authorFromDb);
        return bookRepository.save(book);
    }

    public List<Book> findBooksByFilter(BookFilterType bookFilterType, String filterValue) {
        switch (bookFilterType) {
            case NAME -> {
                return bookRepository.findByName(filterValue);
            }
            case ID -> {
                return bookRepository.findAllById(List.of(Integer.parseInt(filterValue)));
            }
            case AUTHOR_NAME -> {
                return bookRepository.findByAuthorName(filterValue);
            }
            case COST -> {
                return bookRepository.findByCost(Integer.parseInt(filterValue));
            }
            case GENRE -> {
                return bookRepository.findByGenre(filterValue);
            }
        }
        return null;
    }
}

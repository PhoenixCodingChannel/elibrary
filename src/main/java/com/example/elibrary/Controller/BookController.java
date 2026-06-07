package com.example.elibrary.Controller;

import com.example.elibrary.Service.BookService;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.BookFilterType;
import com.example.elibrary.models.request.BookCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        return new ResponseEntity(bookService.saveBook(bookCreateRequest), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("filterType")BookFilterType bookFilterType,
                                    @RequestParam("filterValue") String filterValue){
        return bookService.findBooksByFilter(bookFilterType,filterValue);
    }
}

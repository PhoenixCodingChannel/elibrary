package com.example.elibrary.models.request;

import com.example.elibrary.models.Author;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookCreateRequest {
    @NotBlank
    private String name;
    @Positive
    private int cost;
    @NotNull
    private Genre genre;
    @NotNull
    private Author author;

    public Book toBook() {
        return  Book.builder()
                .name(this.name)
                .cost(this.cost)
                .genre(this.genre)
                .author(this.author).build();
    }
}

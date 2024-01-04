package com.startsteps.controller;

import com.startsteps.model.Book;
import com.startsteps.payload.CreateBookRequest;
import com.startsteps.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBook() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public void createBook(@RequestBody CreateBookRequest createBookRequest) {
        bookService.createBook(createBookRequest);
    }
}

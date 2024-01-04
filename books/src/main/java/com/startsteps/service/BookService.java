package com.startsteps.service;

import com.startsteps.model.Book;
import com.startsteps.payload.CreateBookRequest;
import com.startsteps.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return book.get();
    }

    public void createBook(CreateBookRequest createBookRequest) {
        Book book = new Book(createBookRequest.getName(),
                createBookRequest.getAuthor());
        bookRepository.save(book);
    }

}

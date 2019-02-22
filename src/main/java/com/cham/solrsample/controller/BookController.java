package com.cham.solrsample.controller;

import com.cham.solrsample.domain.Book;
import com.cham.solrsample.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> getBooks(){
        System.out.println("Inside BookController.getBooks");
        Book book1 = new Book("003", "Thor", "Medium", "John Twin","5678");
        bookRepository.save(book1);
        Book returnedBook = bookRepository.findByBookName("Thor");
        System.out.println(returnedBook.toString());
        return Flux.just(returnedBook);
    }

}

package com.cham.solrsample.controller;

import com.cham.solrsample.domain.Book;
import com.cham.solrsample.domain.HazelcastBook;
import com.cham.solrsample.repo.BookRepository;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private final static String mapName="book-map";

    public BookController(){

    }

    @GetMapping(value = "/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Transactional
    public String getBooks(){

        System.out.println("Inside BookController.getBooks");

        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        serializationConfig.addPortableFactoryClass(1, "com.cham.solrsample.domain.PortableFactoryImpl");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<String, HazelcastBook> hazelcastBookIMap = client.getMap(mapName);

        Collection<HazelcastBook> booksFromHcz = hazelcastBookIMap.values(new SqlPredicate( "bookId LIKE '00%'"));

        Iterator itr = booksFromHcz.iterator();

        while (itr.hasNext()){
            HazelcastBook hczBooks= (HazelcastBook)itr.next();
            Book book = new Book(hczBooks.getBookId(), hczBooks.getBookName(),hczBooks.getLevel(),hczBooks.getOwner(), hczBooks.getOwnerId());
            System.out.println(book.toString());
            bookRepository.save(book);
        }

        return "OK";
    }

}

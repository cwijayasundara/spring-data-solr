package com.cham.solrsample.repo;


import com.cham.solrsample.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookRepository extends SolrCrudRepository <Book, String>{

     Book findByBookName(String bookName);

     @Query("id:*?0* OR name:*?0*")
     Page<Book> findByCustomQuery(String searchTerm, Pageable pageable);

}

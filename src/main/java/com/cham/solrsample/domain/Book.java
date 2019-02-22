package com.cham.solrsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@AllArgsConstructor
@ToString
@SolrDocument(solrCoreName = "book")
public class Book {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "book_name", type = "string")
    private String bookName;

    @Indexed(name = "level", type = "string")
    private String level;

    @Indexed(name = "owner", type = "string")
    private String owner;

    @Indexed(name = "ownerId", type = "string")
    private String ownerId;
}

/*----------------------------------------------------------------------------*/
/* Source File:   INMEMORYBOOKREPOSITORY.JAVA                                 */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.repository;

import com.polarbookshop.catalogservice.domain.Book;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * Implements the {@link BookRepository} contract with an in-memory store for the Book Catalog.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Repository
public class InMemoryBookRepository implements BookRepository {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn)
            ? Optional.of(books.get(isbn))
            : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);

        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}

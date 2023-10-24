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

/**
 * Implements the {@link BookRepository} contract with an in-memory store for the Book Catalog.
 *
 * <p><b>NOTE:</b>On Oct.23/2023, the service uses Spring Data JDBC, thus we no longer need this implementation
 * instead of removing this class from the project, we leave this as a reference material and the only
 * thing to remove is actually the annotation or comment it out. And also the implements clause.</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
// Uncomment to enable using this class to inject where necessary @Repository
public class InMemoryBookRepository {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    public Iterable<Book> findAll() {
        return books.values();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn)
            ? Optional.of(books.get(isbn))
            : Optional.empty();
    }

    public boolean existsByIsbn(String isbn) {
        return books.get(isbn) != null;
    }

    public Book save(Book book) {
        books.put(book.isbn(), book);

        return book;
    }

    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}

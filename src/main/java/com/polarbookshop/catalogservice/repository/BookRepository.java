/*----------------------------------------------------------------------------*/
/* Source File:   BOOKREPOSITORY.JAVA                                         */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.repository;

import com.polarbookshop.catalogservice.domain.Book;
import java.util.Optional;

/**
 * Defines the operations to access data for Books.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public interface BookRepository {
    /**
     * Retrieves a list of registered Books.
     *
     * @return A book list.
     * @see Book
     */
    Iterable<Book> findAll();

    /**
     * Looks for the book {@code isbn} in the catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return The Book information details if present or {@code BookNotFoundException} if not present.
     * @See BookNotFoundException
     * @see Book
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Determines if the {@code isbn} is present in the Book Catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return True if present.
     */
    boolean existsByIsbn(String isbn);

    /**
     * Stores the data information for a {@link Book} in the catalog.
     *
     * @param book Contains the {@link Book} inforamtion to be edited in the Book Catalog.
     * @return Saved {@link Book} data.
     */
    Book save(Book book);

    /**
     * Removes given {@link Book} {@code isbn} from the Book Catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     */
    void deleteByIsbn(String isbn);
}

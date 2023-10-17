/*----------------------------------------------------------------------------*/
/* Source File:   BOOKSERVICE.JAVA                                            */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.service;

import com.polarbookshop.catalogservice.domain.Book;

/**
 * Defines the contract for the operations to handle Books.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public interface BookService {
    /**
     * Retrieves a list of registered Books.
     *
     * @return A book list.
     * @see Book
     */
    Iterable<Book> viewBookList();

    /**
     * Looks for the book {@code isbn} in the catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return The Book information details if present or {@code BookNotFoundException} if not present.
     * @see Book
     */
    Book viewBookDetails(String isbn);

    /**
     * Register a new {@link Book} into the catalog.
     *
     * @param book Contains the {@link Book} information to be registered in the Book Catalog.
     * @return
     * @see Book
     */
    Book addBookToCatalog(Book book);

    /**
     * Removes given {@link Book} {@code isbn} from the Book Catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     */
    void removeBookFromCatalog(String isbn);

    /**
     * Edits data for the {@link Book}.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @param book Contains the {@link Book} information to be edited in the Book Catalog.
     * @return Modified {@link Book} data.
     */
    Book editBookDetails(String isbn, Book book);
}

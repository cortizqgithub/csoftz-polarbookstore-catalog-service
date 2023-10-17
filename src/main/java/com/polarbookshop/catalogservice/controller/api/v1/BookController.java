/*----------------------------------------------------------------------------*/
/* Source File:   BOOKCONTROLLER.JAVA                                         */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.controller.api.v1;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes as a REST ApI the Book Catalog.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    /**
     * Constructor with parameters.
     *
     * @param bookService Holds a reference to the book management service.
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves a list of available books in the catalog.
     * <p>{@code GET: api/v1/books}</p>
     *
     * @return List of available books.
     * @see Book
     */
    @GetMapping
    public Iterable<Book> viewBookList() {
        return bookService.viewBookList();
    }

    /**
     * Retrieves a book by its {@code isbn}.
     * <p>{@code GET: api/v1/books/{isbn}}</p>
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return Requested information for the book associated with the {@code isbn}.
     * @see Book
     */
    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    /**
     * Registers a new book in the catalog. If the action is successful a 201 HTTP Status is set.
     *
     * @param book Contains the {@link Book} information to be registered in the Book Catalog.
     * @return The registered book in the catalog.
     * @see Book
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    /**
     * Remove a book by its {@code isbn} from the catalog. If successful then it returns 204 HTTP Status.
     * <p>{@code DELETE: api/v1/books/{isbn}}</p>
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @see Book
     */
    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    /**
     * Edits/Updates the information for the given {@code isbn}.
     * <p>{@code PUT: api/v1/books/{isbn}}</p>
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @param book Contains the {@link Book} information to be registered in the Book Catalog.
     * @return Edited book record.
     * @see Book
     */
    @PutMapping("/{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}

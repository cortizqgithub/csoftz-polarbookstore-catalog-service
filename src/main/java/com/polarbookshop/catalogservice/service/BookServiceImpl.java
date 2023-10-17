/*----------------------------------------------------------------------------*/
/* Source File:   BOOKSERVICE.JAVA                                            */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.service;

import com.polarbookshop.catalogservice.common.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.common.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

/**
 * Defines the operations to handle Books.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    /**
     * Constructor with parameters.
     *
     * @param bookRepository Reference to the data management for Books.
     */
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    @Override
    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }

        return bookRepository.save(book);
    }

    @Override
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
            .map(existingBook -> {
                var bookToUpdate = new Book(
                    existingBook.isbn(),
                    book.title(),
                    book.author(),
                    book.price());
                return bookRepository.save(bookToUpdate);
            })
            .orElseGet(() -> addBookToCatalog(book));
    }
}

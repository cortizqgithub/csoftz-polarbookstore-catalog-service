/*----------------------------------------------------------------------------*/
/* Source File:   BOOKSERVICEIMPLTEST.JAVA       */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.service;

//import static org.assertj.core.api.Assertions.*;

import static com.polarbookshop.catalogservice.common.consts.ExceptionConstants.BOOK_WITH_ISBN_ALREADY_EXISTS;
import static com.polarbookshop.catalogservice.common.consts.ExceptionConstants.BOOK_WITH_ISBN_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.polarbookshop.catalogservice.common.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.common.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Performs verifications on the {@link BookService} contract.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final String ISBN = "1234561232";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";

    private static final double PRICE = 9.90;

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void beforeEach() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Verify when a book is already created an exception is thrown.")
    void whenBookToCreateAlreadyExistsThenThrows() {
        var bookIsbn = ISBN;
        var bookToCreate = new Book(bookIsbn, TITLE, AUTHOR, PRICE);

        when(bookRepository.existsByIsbn(bookIsbn)).thenReturn(true);

        assertThatThrownBy(() -> bookService.addBookToCatalog(bookToCreate))
            .isInstanceOf(BookAlreadyExistsException.class)
            .hasMessage(String.format(BOOK_WITH_ISBN_ALREADY_EXISTS, bookIsbn));

        verify(bookRepository).existsByIsbn(bookIsbn);
    }

    @Test
    @DisplayName("Verify a book does not exist then throw an exception.")
    void whenBookToReadDoesNotExistThenThrows() {
        var bookIsbn = ISBN;

        when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.viewBookDetails(bookIsbn))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessage(String.format(BOOK_WITH_ISBN_NOT_FOUND, bookIsbn));

        verify(bookRepository).findByIsbn(bookIsbn);
    }
}
/*----------------------------------------------------------------------------*/
/* Source File:   BOOKCONTROLLERINTEGRATIONTEST.JAVA                          */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.controller.api.v1;

import static com.polarbookshop.catalogservice.common.consts.ExceptionConstants.BOOK_WITH_ISBN_NOT_FOUND;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.SLASH;
import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Performs Full Integration Test (uses the whole Spring Context) for {@link BookController}.
 * <p>When using {@link SpringBootTest}, this annotation loads all registered beans in the application, even those not needed
 * for the {@link BookController}</p>
 * <p><b>Path:</b>{@code api/v1/books}</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class BookControllerIntegrationTest {
    private static final String ISBN = "1231231230";
    private static final String ISBN_TWO = "1231231231";
    private static final String ISBN_THREE = "1231231232";
    private static final String ISBN_FOUR = "1231231233";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String API_BOOK_PATH = "/api/v1/books";

    private static final double PRICE = 9.90;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Verify when making a request with an ISBN, a book is returned.")
    void whenGetRequestWithIdThenBookReturned() {
        var bookIsbn = ISBN;
        var bookToCreate = new Book(bookIsbn, TITLE, AUTHOR, PRICE);

        var expectedBook = webTestClient
            .post()
            .uri(API_BOOK_PATH)
            .bodyValue(bookToCreate)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
            .returnResult().getResponseBody();

        webTestClient
            .get()
            .uri(API_BOOK_PATH + SLASH + bookIsbn)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(Book.class).value(actualBook -> {
                assertThat(actualBook).isNotNull();
                assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
            });
    }

    @Test
    @DisplayName("Verify when a book is created the action the status is CREATED")
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book(ISBN_TWO, TITLE, AUTHOR, PRICE);

        webTestClient
            .post()
            .uri(API_BOOK_PATH)
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class).value(actualBook -> {
                assertThat(actualBook).isNotNull();
                assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
            });
    }

    @Test
    @DisplayName("Verify when a book is edit then the status is CREATED")
    void whenPutRequestThenBookUpdated() {
        var bookIsbn = ISBN_THREE;
        var bookToCreate = new Book(bookIsbn, TITLE, AUTHOR, PRICE);

        var createdBook = webTestClient
            .post()
            .uri(API_BOOK_PATH)
            .bodyValue(bookToCreate)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
            .returnResult().getResponseBody();
        var bookToUpdate = new Book(createdBook.isbn(), createdBook.title(), createdBook.author(), 7.95);

        webTestClient
            .put()
            .uri(API_BOOK_PATH + SLASH + bookIsbn)
            .bodyValue(bookToUpdate)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Book.class).value(actualBook -> {
                assertThat(actualBook).isNotNull();
                assertThat(actualBook.price()).isEqualTo(bookToUpdate.price());
            });
    }

    @Test
    @DisplayName("Verify when a delete a book then the status is CREATED")
    void whenDeleteRequestThenBookDeleted() {
        var bookIsbn = ISBN_FOUR;
        var bookToCreate = new Book(bookIsbn, TITLE, AUTHOR, PRICE);

        webTestClient
            .post()
            .uri(API_BOOK_PATH)
            .bodyValue(bookToCreate)
            .exchange()
            .expectStatus().isCreated();

        webTestClient
            .delete()
            .uri(API_BOOK_PATH + SLASH + bookIsbn)
            .exchange()
            .expectStatus().isNoContent();

        webTestClient
            .get()
            .uri(API_BOOK_PATH + SLASH + bookIsbn)
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(String.class).value(errorMessage ->
                assertThat(errorMessage).isEqualTo(String.format(BOOK_WITH_ISBN_NOT_FOUND, bookIsbn)));
    }
}
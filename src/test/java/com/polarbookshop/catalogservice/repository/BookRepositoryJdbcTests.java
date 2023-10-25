/*----------------------------------------------------------------------------*/
/* Source File:   BOOKREPOSITORYJDBCTESTS.JAVA                                */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.23/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.repository;

import static com.polarbookshop.catalogservice.common.consts.TestcontainerConstants.POSTGRESQL_DOCKER_VERSION;
import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.common.config.DataConfig;
import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Spring Data JDBC sliced integration test using TestContainers for integration to the backend data service (PostgreSQL).
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Testcontainers
@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcTests {
    private static final String ISBN = "1234561237";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";

    public static final double PRICE_12_90 = 12.90;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRESQL_DOCKER_VERSION);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    @DisplayName("Verify the Testcontainer has access to the server.")
    void shouldEstablishConnection() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    @DisplayName("Verify it should find a book by ISBN when existing.")
    void shouldFindBookByIsbnWhenExisting() {
        var bookIsbn = ISBN;
        var book = Book.of(bookIsbn, TITLE, AUTHOR, PRICE_12_90, null);

        jdbcAggregateTemplate.insert(book);

        var actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}

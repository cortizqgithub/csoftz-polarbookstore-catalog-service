/*----------------------------------------------------------------------------*/
/* Source File:   BOOK.JAVA                                                   */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.domain;


import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_AUTHOR_MUST_BE_DEFINED;
import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_ISBN_FORMAT_MUST_BE_VALID;
import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_ISBN_MUST_BE_DEFINED;
import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_PRICE_MUST_BE_DEFINED;
import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO;
import static com.polarbookshop.catalogservice.common.consts.BookValidationConstants.BOOK_TITLE_MUST_BE_DEFINED;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.PUBLISHER;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Performs Book Validation tests.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
class BookValidationTests {
    private static final String ISBN = "1234567890";
    private static final String ISBN_INVALID = "a234567890";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String AUTHOR_EMPTY = "";

    private static final double PRICE = 9.90;
    private static final double PRICE_ZERO = 0.0;

    private static final int INT_ONE = 1;

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Verify when all fields are correct then validation succeeds")
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = Book.of(ISBN, TITLE, AUTHOR, PRICE, PUBLISHER);
        var violations = validator.validate(book);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Verify when ISBN is not defined then validation fails.")
    void whenIsbnNotDefinedThenValidationFails() {
        var book = Book.of(AUTHOR_EMPTY, TITLE, AUTHOR, PRICE, PUBLISHER);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(2);
        assertThat(
            violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList())
            .contains(BOOK_ISBN_MUST_BE_DEFINED)
            .contains(BOOK_ISBN_FORMAT_MUST_BE_VALID);
    }

    @Test
    @DisplayName("Verify ISBN is defined but it is not correct then fails.")
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = Book.of(ISBN_INVALID, TITLE, AUTHOR, PRICE, PUBLISHER);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_ISBN_FORMAT_MUST_BE_VALID);
    }

    @Test
    @DisplayName("Verify when Title is not defined then validation fails.")
    void whenTitleIsNotDefinedThenValidationFails() {
        var book = Book.of(ISBN, AUTHOR_EMPTY, AUTHOR, PRICE, PUBLISHER);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_TITLE_MUST_BE_DEFINED);
    }

    @Test
    @DisplayName("Verify when Author is not defined then validation fails.")
    void whenAuthorIsNotDefinedThenValidationFails() {
        var book = Book.of(ISBN, TITLE, AUTHOR_EMPTY, PRICE, null);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_AUTHOR_MUST_BE_DEFINED);
    }

    @Test
    @DisplayName("Verify when Price is not defined then validation fails.")
    void whenPriceIsNotDefinedThenValidationFails() {
        var book = Book.of(ISBN, TITLE, AUTHOR, null, null);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_DEFINED);
    }

    @Test
    @DisplayName("Verify when Price is defined but it has a zero value then validation fails.")
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = Book.of(ISBN, TITLE, AUTHOR, PRICE_ZERO, null);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO);
    }

    @Test
    @DisplayName("Verify when Price is defined but it has a negative value then validation fails.")
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = Book.of(ISBN, TITLE, AUTHOR, -PRICE, null);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO);
    }
}

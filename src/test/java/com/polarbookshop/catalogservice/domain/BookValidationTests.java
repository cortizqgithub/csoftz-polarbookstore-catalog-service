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
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
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
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book(ISBN, TITLE, AUTHOR, PRICE);
        var violations = validator.validate(book);

        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var book = new Book(AUTHOR_EMPTY, TITLE, AUTHOR, PRICE);
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
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Book(ISBN_INVALID, TITLE, AUTHOR, PRICE);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_ISBN_FORMAT_MUST_BE_VALID);
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var book = new Book(ISBN, AUTHOR_EMPTY, AUTHOR, PRICE);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_TITLE_MUST_BE_DEFINED);
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        var book = new Book(ISBN, TITLE, AUTHOR_EMPTY, PRICE);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_AUTHOR_MUST_BE_DEFINED);
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        var book = new Book(ISBN, TITLE, AUTHOR, null);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_DEFINED);
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = new Book(ISBN, TITLE, AUTHOR, PRICE_ZERO);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO);
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = new Book(ISBN, TITLE, AUTHOR, -PRICE);
        var violations = validator.validate(book);

        assertThat(violations).hasSize(INT_ONE);
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo(BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO);
    }
}

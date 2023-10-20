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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Defines the entity for Books.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public record Book(
    @NotBlank(message = BOOK_ISBN_MUST_BE_DEFINED)
    @Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})$",
        message = BOOK_ISBN_FORMAT_MUST_BE_VALID
    )
    String isbn,

    @NotBlank(
        message = BOOK_TITLE_MUST_BE_DEFINED
    )
    String title,

    @NotBlank(message = BOOK_AUTHOR_MUST_BE_DEFINED)
    String author,

    @NotNull(message = BOOK_PRICE_MUST_BE_DEFINED)
    @Positive(message = BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO)
    Double price) {
}

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
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/**
 * Defines the entity for Books.
 * <p>The field {@code isbn} is the primary key (natural key or business key).</p>
 * <p>The field {@code id} is a techical key (or surrogate key).</p>
 * <p>The field {@code version} is used to control a concurrent record update.</p>
 *
 * @param id               Represents the technical key.
 * @param isbn             Represents the primary key.
 * @param title            Represents the given name for the book.
 * @param author           Represents the writer of the book.
 * @param price            Indicates the value of the book.
 * @param version          Controls optimistic lock for concurrent updates.
 * @param createdDate      Audits record creation.
 * @param lastModifiedDate Audits record update.
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public record Book(
    @Id
    Long id,


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
    Double price,

    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate,

    @Version
    int version) {

    /**
     * Performs a record instance creation of a Book without the managed Spring Data fields 'id', and 'version'.
     * It can be used to ease the creation of test data.
     *
     * @param isbn   Represents the primary key.
     * @param title  Represents the given name for the book.
     * @param author Represents the writer of the book.
     * @param price  Indicates the value of the book.
     * @return
     */
    public static Book of(String isbn, String title, String author, Double price) {
        return new Book(null, isbn, title, author, price, null, null, 0);
    }
}

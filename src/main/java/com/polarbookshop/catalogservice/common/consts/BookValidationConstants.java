/*----------------------------------------------------------------------------*/
/* Source File:   BOOKVALIDATIONCONSTANTS.JAVA                                */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.consts;

/**
 * Book Validation Constants.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class BookValidationConstants {
    public static final String BOOK_ISBN_MUST_BE_DEFINED = "The book ISBN must be defined.";
    public static final String BOOK_ISBN_FORMAT_MUST_BE_VALID = "The ISBN format must be valid.";
    public static final String BOOK_TITLE_MUST_BE_DEFINED = "The book title must be defined.";
    public static final String BOOK_AUTHOR_MUST_BE_DEFINED = "The book author must be defined.";
    public static final String BOOK_PRICE_MUST_BE_DEFINED = "The book price must be defined.";
    public static final String BOOK_PRICE_MUST_BE_GREATER_THAN_ZERO = "The book price must be greater than zero.";

    /**
     * This is a utility class thus we must avoid to instantiate this.
     */
    private BookValidationConstants() {
    }
}

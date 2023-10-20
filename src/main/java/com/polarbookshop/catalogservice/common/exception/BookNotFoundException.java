/*----------------------------------------------------------------------------*/
/* Source File:   BOOKNOTFOUNDEXCEPTION.JAVA                                  */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.exception;

import static com.polarbookshop.catalogservice.common.consts.ExceptionConstants.BOOK_WITH_ISBN_NOT_FOUND;

/**
 * Indicates that a book is not registered in the Book Catalog.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super(String.format(BOOK_WITH_ISBN_NOT_FOUND, isbn));
    }
}

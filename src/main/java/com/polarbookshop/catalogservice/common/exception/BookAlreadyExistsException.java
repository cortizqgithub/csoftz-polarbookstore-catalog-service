/*----------------------------------------------------------------------------*/
/* Source File:   BOOKALREADYEXISTSEXCEPTION.JAVA                             */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.exception;

import static com.polarbookshop.catalogservice.common.consts.ExceptionConstants.BOOK_WITH_ISBN_ALREADY_EXISTS;

/**
 * Indicates that a book is already registered in the Book Catalog.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super(String.format(BOOK_WITH_ISBN_ALREADY_EXISTS, isbn));
    }

}

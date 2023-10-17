/*----------------------------------------------------------------------------*/
/* Source File:   EXCEPTIONCONSTANTS.JAVA                                     */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.consts;

/**
 * Exception related constants.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class ExceptionConstants {

    public static final String BOOK_WITH_ISBN_NOT_FOUND = "The book with ISBN %s was not found.";
    public static final String BOOK_WITH_ISBN_ALREADY_EXISTS = "A book with ISBN [%s] already exists.";

    /**
     * This is a utility class thus we must avoid to instantiate this.
     */
    private ExceptionConstants() {
    }
}

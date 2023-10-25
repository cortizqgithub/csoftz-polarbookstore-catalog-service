/*----------------------------------------------------------------------------*/
/* Source File:   GLOBALCONSTANTS.JAVA                                        */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.consts;

/**
 * General purpose application constants.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class GlobalConstants {

    public static final String SLASH = "/";
    public static final String BOOK_ISBN_ONE = "1234567891";
    public static final String BOOK_ISBN_TWO = "1234567892";
    public static final String BOOK_TITLE_ONE = "Northern Lights";
    public static final String BOOK_TITLE_TWO = "Polar Journey";
    public static final String BOOK_AUTHOR_ONE = "Lyra Silverstar";
    public static final String BOOK_AUTHOR_TWO = "Iorek Polarson";
    public static final String PUBLISHER = "Polarsophia";

    public static final double BOOK_PRICE_ONE = 9.90;
    public static final double BOOK_PRICE_TWO = 12.90;

    /**
     * This is a utility class thus we must avoid to instantiate this.
     */
    private GlobalConstants() {
    }
}

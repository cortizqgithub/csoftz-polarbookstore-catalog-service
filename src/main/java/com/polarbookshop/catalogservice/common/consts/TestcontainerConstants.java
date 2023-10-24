/*----------------------------------------------------------------------------*/
/* Source File:   TESTCONTAINERCONSTANTS.JAVA                                 */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.24/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.consts;


public class TestcontainerConstants {

    public static String POSTGRESQL_DOCKER_VERSION = "postgres:16.0";

    /**
     * This is a utility class thus we must avoid to instantiate this.
     */
    private TestcontainerConstants() {
    }
}

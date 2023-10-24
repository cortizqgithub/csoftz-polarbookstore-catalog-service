/*----------------------------------------------------------------------------*/
/* Source File:   DATACONFIG.JAVA                                             */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.23/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

/**
 * Spring Data JDBC configuration.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Configuration
@EnableJdbcAuditing
public class DataConfig {
}

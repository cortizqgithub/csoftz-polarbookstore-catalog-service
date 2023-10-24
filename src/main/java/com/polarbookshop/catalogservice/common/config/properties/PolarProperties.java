/*----------------------------------------------------------------------------*/
/* Source File:   POLARPROPERTIES.JAVA                                        */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.21/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Groups properties (key/value).
 * <p>As we use a prefix here, the property key is 'polar.greeting'.</p>
 * <p>Add the {@code org.springframework.boot:spring-boot-configuration-processor} to
 * generate metadata for the properties so the IDE can give you hints.</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    /**
     * A message to welcome users.
     */
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}

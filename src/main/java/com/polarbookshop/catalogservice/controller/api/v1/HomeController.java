/*----------------------------------------------------------------------------*/
/* Source File:   APPLICATION.JAVA                                            */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.23/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.controller.api.v1;

import com.polarbookshop.catalogservice.common.config.PolarProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point for running the application.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
    private final PolarProperties polarProperties;

    /**
     * Constructor with parameters.
     * @param polarProperties Injects the 'polar' group of properties.
     */
    public HomeController(PolarProperties polarProperties) {
        this.polarProperties = polarProperties;
    }

    /**
     * Issues a welcome message.
     *
     * @return Text for the welcome message.
     */
    @GetMapping
    public String getGreeting() {
        return polarProperties.getGreeting();
    }
}

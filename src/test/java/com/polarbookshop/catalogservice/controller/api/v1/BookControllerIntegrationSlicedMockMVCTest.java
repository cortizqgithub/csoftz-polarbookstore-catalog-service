/*----------------------------------------------------------------------------*/
/* Source File:   BOOKCONTROLLERINTEGRATIONSLICEDMOCKMVCTEST.JAVA             */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.controller.api.v1;

import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.SLASH;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polarbookshop.catalogservice.common.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Performs Full Integration Test (uses the whole Spring Context) for {@link BookController}.
 * <p>When using {@link WebMvcTest}, this annotation loads only beans the controller uses (includes the web server).
 * for the {@link BookController}</p>
 * <p><b>NOTE:</b>This is an old way of writing unit tests. New way is using the {@code WebTestClient} way.
 * See BookControllerIntegrationSlicedWebTestClientTest. Besides only a test is shown (see BookControllerIntegrationTest
 * for all possible unit tests). Here the web server is mocked.
 * </p>
 * <p><b>Path:</b>{@code api/v1/books}</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@WebMvcTest(BookController.class)
class BookControllerIntegrationSlicedMockMVCTest {
    private static final String ISBN = "73737313940";
    private static final String API_BOOK_PATH = "/api/v1/books";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Verify a book not exist returns 404.")
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        given(bookService.viewBookDetails(ISBN)).willThrow(BookNotFoundException.class);
        mockMvc
            .perform(get(API_BOOK_PATH + SLASH + ISBN))
            .andExpect(status().isNotFound());
    }
}

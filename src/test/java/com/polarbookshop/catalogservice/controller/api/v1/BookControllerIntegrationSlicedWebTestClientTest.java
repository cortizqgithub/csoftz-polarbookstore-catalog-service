/*----------------------------------------------------------------------------*/
/* Source File:   BOOKCONTROLLERINTEGRATIONSLICEDWEBTESTCLIENTTEST.JAVA       */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.controller.api.v1;

import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.SLASH;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.polarbookshop.catalogservice.common.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

/**
 * Performs Full Integration Test (uses the whole Spring Context) for {@link BookController}.
 * <p>When using {@link WebMvcTest}, this annotation loads only beans the controller uses (includes the web server).
 * for the {@link BookController}</p>
 * <p><b>NOTE:</b>This is an old way of writing unit tests. New way is using the {@code WebTestClient} way.
 * See BookControllerIntegrationSlicedWebTestClientTest. Besides only a test is shown (see BookControllerIntegrationTest
 * for all possible unit tests.
 * </p>
 * <p><b>Path:</b>{@code api/v1/books}</p>
 * <p>Some reference links follow:
 * <ul>
 *     <li><a href="https://rieckpil.de/test-your-spring-mvc-controller-with-webtestclient-against-mockmvc/">Test Your Spring MVC Controller with the WebTestClient and MockMvc</a></li>
 *     <li><a href="https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/">Spring 5 WebClient and WebTestClient Tutorial with Examples</a></li>
 * </ul>
 * </p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@WebMvcTest(BookController.class)
class BookControllerIntegrationSlicedWebTestClientTest {
    private static final String ISBN = "73737313940";
    private static final String API_BOOK_PATH = "/api/v1/books";

    private WebTestClient client;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void beforeEach(@Autowired MockMvc mockMvc) {
        this.client = MockMvcWebTestClient
            .bindTo(mockMvc)
            .build();
    }

    @Test
    @DisplayName("Verify a book not exist returns 404.")
    void whenGetBookNotExistingThenShouldReturn404() {
        given(bookService.viewBookDetails(ISBN)).willThrow(BookNotFoundException.class);

        client.get()
            .uri(API_BOOK_PATH + SLASH + ISBN)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus().isNotFound();
    }
}

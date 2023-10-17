/*----------------------------------------------------------------------------*/
/* Source File:   BOOKJSONTESTS.JAVA                                          */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

/**
 * Perform JSON validation on the Book model.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@JsonTest
public class BookJsonTests {
    private static final String ISBN = "1234567890";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";

    private static final double PRICE = 9.90;

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book(ISBN, TITLE, AUTHOR, PRICE);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
            .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
            .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
            .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
            .isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.90
            }
            """;
        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(new Book(ISBN, TITLE, AUTHOR, PRICE));
    }
}

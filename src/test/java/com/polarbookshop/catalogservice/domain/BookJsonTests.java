/*----------------------------------------------------------------------------*/
/* Source File:   BOOKJSONTESTS.JAVA                                          */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.13/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.domain;

import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.PUBLISHER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
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
    private static final String ISBN_JPATH = "@.isbn";
    private static final String TITLE_JPATH = "@.title";
    private static final String AUTHOR_JPATH = "@.author";
    private static final String PRICE_JPATH = "@.price";

    private static final double PRICE = 9.90;

    @Autowired
    private JacksonTester<Book> json;

    @Test
    @DisplayName("Verify we can serialize to JSON.")
    void testSerialize() throws Exception {
        var book = Book.of(ISBN, TITLE, AUTHOR, PRICE, PUBLISHER);

        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue(ISBN_JPATH)
            .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue(TITLE_JPATH)
            .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue(AUTHOR_JPATH)
            .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue(PRICE_JPATH)
            .isEqualTo(book.price());
    }

    @Test
    @DisplayName("Verify we can deserialize from JSON.")
    void testDeserialize() throws Exception {
        var content = """
            {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.90,
                "publisher": "Polarsophia"
            }
            """;

        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book.of(ISBN, TITLE, AUTHOR, PRICE, PUBLISHER));
    }
}

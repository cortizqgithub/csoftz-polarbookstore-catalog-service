/*----------------------------------------------------------------------------*/
/* Source File:   BOOKDATALOADER.JAVA                                         */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.21/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.common.infrastructure;

import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_AUTHOR_ONE;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_AUTHOR_TWO;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_ISBN_ONE;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_ISBN_TWO;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_PRICE_ONE;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_PRICE_TWO;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_TITLE_ONE;
import static com.polarbookshop.catalogservice.common.consts.GlobalConstants.BOOK_TITLE_TWO;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;
import java.util.List;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Load test data (as a feature toggle testdata via profile).
 * <p><b>NOTE:</b>This is only activated if and only if the {@code testdata} Spring profile is activated.</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    /**
     * Constructor with parameters.
     *
     * @param bookRepository Indicates the reference to the book service repository instance.
     */
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Performs the test data generation when an ApplicationReadyEvent is sent -- that is when the
     * application startup phase is completed.
     *
     * @see ApplicationReadyEvent
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        bookRepository.saveAll(
            List.of(
                Book.of(BOOK_ISBN_ONE, BOOK_TITLE_ONE, BOOK_AUTHOR_ONE, BOOK_PRICE_ONE),
                Book.of(BOOK_ISBN_TWO, BOOK_TITLE_TWO, BOOK_AUTHOR_TWO, BOOK_PRICE_TWO)
            )
        );
    }
}

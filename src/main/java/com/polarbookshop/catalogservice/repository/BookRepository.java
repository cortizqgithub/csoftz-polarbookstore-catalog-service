/*----------------------------------------------------------------------------*/
/* Source File:   BOOKREPOSITORY.JAVA                                         */
/* Copyright (c), 2023 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Oct.12/2023  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.polarbookshop.catalogservice.repository;

import com.polarbookshop.catalogservice.domain.Book;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defines the operations to access data for {@code Books}.
 * <p><b>NOTE:</b>As of Oct.23/2023, the definition is replaced by Spring Data semantics, if you want to
 * use the prior version, see commit be2522708a549351e94342a15ca0edf15b3a85fa</p>
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     * Looks for the book {@code isbn} in the catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return The Book information details if present or {@code BookNotFoundException} if not present.
     * @See BookNotFoundException
     * @see Book
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Determines if the {@code isbn} is present in the Book Catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     * @return True if present.
     */
    boolean existsByIsbn(String isbn);

    /**
     * Removes given {@link Book} {@code isbn} from the Book Catalog.
     *
     * @param isbn Indicates the Unique book identifier to look for.
     */
    @Modifying
    @Transactional
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}

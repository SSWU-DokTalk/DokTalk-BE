package database.doktalk.domain.book.repository;

import database.doktalk.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookName(String bookName);

    @Query("SELECT DISTINCT b FROM Book b WHERE b.bookName LIKE%:keyword%")
    List<Book> findBooksByKeyword(@Param("keyword") String keyword);

}
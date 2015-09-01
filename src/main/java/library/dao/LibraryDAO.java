package library.dao;

import library.mapper.BookMapper;
import library.representation.Book;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by raghunathan.mj on 23/07/15.
 */
public interface LibraryDAO {
    @Mapper(BookMapper.class)
    @SqlQuery("select * from Book where isbn = :isbn")
    Book getBookById(@Bind("isbn") int isbn);

    @Mapper(BookMapper.class)
    @SqlQuery("select * from Book;")
    List<Book> getAllBooks();

    @GetGeneratedKeys
    @SqlUpdate("insert into Book (isbn, title) values (:isbn, :title)")
    int createBook(@Bind("isbn") int isbn, @Bind("title") String title);

    @SqlUpdate("update Book set title = :title where isbn = :isbn")
    void updateBook(@Bind("isbn") int isbn, @Bind("title") String title);

    @SqlUpdate("delete from Book where isbn = :isbn")
    void deleteBook(@Bind("isbn") int isbn);

    @SqlUpdate("delete from AuthorBookRelation where AuthorBookRelation.isbn = :isbn;")
    void adjustAuthBookForBookDeletion(@Bind("isbn") int isbn);
}

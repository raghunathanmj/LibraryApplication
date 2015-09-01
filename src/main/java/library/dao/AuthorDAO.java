package library.dao;

import library.representation.Author;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by raghunathan.mj on 27/07/15.
 */
public interface AuthorDAO {
    @SqlQuery("select * from Author where authorID = :authorID")
    Author getAuthorById(@Bind("authorID") int authorID);

    @GetGeneratedKeys
    @SqlUpdate("insert into Author (authorID, authorName) values (:authorID, :name);")
    int createAuthor(@Bind("authorID") int authorID, @Bind("name") String name);

    @SqlUpdate("update Author set authorName = :name where authorID = :authorID")
    void updateAuthor(@Bind("authorID") int authorID, @Bind("name") String name);

    @SqlUpdate("delete from Author where authorID = :authorID")
    void deleteAuthor(@Bind("authorID") int authorID);

    @SqlUpdate("delete from AuthorBookRelation where AuthorBookRelation.authorID = :authorID;")
    void adjustAuthBookForAuthDeletion(@Bind("authorID") int authorID);

    @SqlUpdate("delete from Book where Book.isbn not in(select isbn from AuthorBookRelation);")
    void adjustBookForAuthDeletion();
}

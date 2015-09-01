package library.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by raghunathan.mj on 27/07/15.
 */
public interface AuthorBookRelationDAO {
    @GetGeneratedKeys
    @SqlUpdate("insert into AuthorBookRelation (isbn, authorID) values (:isbn, :authorID)")
    int newBookAuthors(@Bind("isbn") int isbn, @Bind("authorID") int authorID);

}

package library.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import library.mapper.CurrentUserMapper;
/**
 * Created by raghunathan.mj on 30/07/15.
 */
public interface CurrentUserDAO {
    @GetGeneratedKeys
    @SqlUpdate("insert into CurrentUser (userHeader, username) values(:username, :userHeader);")
    int createCurrentUser(@Bind("username") String username, @Bind("userHeader") String userHeader);

    @Mapper(CurrentUserMapper.class)
    @SqlQuery("select username from CurrentUser where userHeader = :userHeader;")
    String isValidHeader(@Bind("userHeader") String userHeader);

    @Mapper(CurrentUserMapper.class)
    @SqlQuery("select username from CurrentUser where username = :username;")
    String isLoggedIn(@Bind("username") String username);

    @SqlUpdate("delete from CurrentUser where userHeader = :userHeader")
    void deleteHeader(@Bind("userHeader") String userHeader);
}

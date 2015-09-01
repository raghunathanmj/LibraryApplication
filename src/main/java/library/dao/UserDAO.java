package library.dao;

import library.mapper.UserMapper;
import library.representation.User;
import org.hibernate.annotations.SQLUpdate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by raghunathan.mj on 30/07/15.
 */
public interface UserDAO {
    @GetGeneratedKeys
    @SqlUpdate("insert into LibraryUser (username, userPassword, personName) values(:username, :password,:name);")
    int createUser(@Bind("username") String username, @Bind("password") String password, @Bind("name") String name);

    @Mapper(UserMapper.class)
    @SqlQuery("select * from LibraryUser where username = :username;")
    User getUserByUsername(@Bind("username") String username);
}

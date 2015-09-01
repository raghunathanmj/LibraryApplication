package library.mapper;

import library.representation.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by raghunathan.mj on 23/07/15.
 */

public class UserMapper implements ResultSetMapper<User>{
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        //System.out.println(r.get);
        return new User( r.getString("username"), r.getString("userPassword"), r.getString("personName"));
    }
}
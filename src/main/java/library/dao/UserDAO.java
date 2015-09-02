package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.User;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by raghunathan.mj on 19/08/15.
 */
public class UserDAO extends AbstractDAO<User>{
    public UserDAO(SessionFactory factory) {
        super(factory);
    }
    public User findById(String username) {
        return get(username);
    }
    public User create(User user) {
        return persist(user);
    }
    public List<User> findAll() {
        return list(namedQuery("library.representation.User.findAll"));
    }
}

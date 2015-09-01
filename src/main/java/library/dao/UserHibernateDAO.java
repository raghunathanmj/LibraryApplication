package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.UserHibernate;
import org.hibernate.SessionFactory;


import java.util.List;

/**
 * Created by raghunathan.mj on 19/08/15.
 */
public class UserHibernateDAO extends AbstractDAO<UserHibernate>{
    public UserHibernateDAO(SessionFactory factory) {
        super(factory);
    }

    public UserHibernate findById(String username) {
        return get(username);
    }

    public UserHibernate create(UserHibernate userHibernate) {
        return persist(userHibernate);
    }

    public List<UserHibernate> findAll() {
        return list(namedQuery("library.representation.UserHibernate.findAll"));
    }
}

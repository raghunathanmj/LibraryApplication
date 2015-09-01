package library.application;

/**
 * Created by raghunathan.mj on 20/07/15.
 */

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import library.configuration.LibraryConfiguration;
import library.dao.*;
import library.filter.LoginRequiredFeature;
import library.filter.LogoutFeature;
import library.representation.UserHibernate;
import library.resource.*;
import org.skife.jdbi.v2.DBI;

public class LibraryApplication extends Application<LibraryConfiguration> {

    private final HibernateBundle<LibraryConfiguration> hibernate = new HibernateBundle<LibraryConfiguration>(UserHibernate.class) {
        public DataSourceFactory getDataSourceFactory(LibraryConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(String[] args) throws Exception {
        new LibraryApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<LibraryConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<LibraryConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/Assets/"));
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) {
        final ViewResource resource1 = new ViewResource();
        environment.jersey().register(resource1);


        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "mysql");

        final LibraryDAO daoLib = jdbi.onDemand(LibraryDAO.class);
        final AuthorBookRelationDAO daoAuthBook = jdbi.onDemand(AuthorBookRelationDAO.class);
        final LibraryResource resourceLib = new LibraryResource(daoLib, daoAuthBook);
        environment.jersey().register(resourceLib);

        final AuthorDAO daoAuth = jdbi.onDemand(AuthorDAO.class);
        final AuthorResource resourceAuth = new AuthorResource(daoAuth);
        environment.jersey().register(resourceAuth);

        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final CurrentUserDAO currentUserDAO = jdbi.onDemand(CurrentUserDAO.class);
        final UserResource resourceUser = new UserResource(userDAO, currentUserDAO);
        environment.jersey().register(resourceUser);

        final CurrentUserDAO currentUserDAO1 = jdbi.onDemand(CurrentUserDAO.class);
        environment.jersey().register(new LoginRequiredFeature(currentUserDAO1));

        final CurrentUserDAO currentUserDAO2 = jdbi.onDemand(CurrentUserDAO.class);
        environment.jersey().register(new LogoutFeature(currentUserDAO2));

        final UserHibernateDAO userHibernateDAO = new UserHibernateDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserHibernateResource(userHibernateDAO));

    }

}

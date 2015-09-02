package library.application;

/**
 * Created by raghunathan.mj on 20/07/15.
 */

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import library.configuration.LibraryConfiguration;
import library.dao.AuthorDAO;
import library.dao.BookDAO;
import library.dao.UserDAO;
import library.representation.Author;
import library.representation.Book;
import library.representation.User;
import library.resource.AuthorResource;
import library.resource.BookResource;
import library.resource.UserResource;
import library.resource.ViewResource;

public class LibraryApplication extends Application<LibraryConfiguration> {

    private final HibernateBundle<LibraryConfiguration> hibernate = new HibernateBundle<LibraryConfiguration>(User.class, Author.class, Book.class) {
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
        final ViewResource resource = new ViewResource();
        environment.jersey().register(resource);

        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(userDAO));

        final AuthorDAO authorDAO = new AuthorDAO(hibernate.getSessionFactory());
        environment.jersey().register(new AuthorResource(authorDAO));

        final BookDAO bookDAO = new BookDAO(hibernate.getSessionFactory());
        environment.jersey().register(new BookResource(bookDAO));

    }

}

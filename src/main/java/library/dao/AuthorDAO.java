package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.Author;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by raghunathan.mj on 01/09/15.
 */
public class AuthorDAO extends AbstractDAO<Author> {
    public AuthorDAO(SessionFactory factory) {super(factory);}
    public Author findById(int id) {return get(id);}
    public Author create(Author author) {return persist(author);}
    public List<Author> findAll() {return list(namedQuery("library.representation.Author.findAll"));}
}

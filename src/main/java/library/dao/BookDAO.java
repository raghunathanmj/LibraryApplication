package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.Book;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by raghunathan.mj on 01/09/15.
 */
public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory factory) {super(factory);}

    public Book findById(int isbn) {return get(isbn);}

    public Book create(Book book) {return persist(book);}

    public List<Book> findAll() {return list(namedQuery("library.representation.Book.findAll"));}

    public List<Book> matchByName(String str) {
        Query q = currentSession().getNamedQuery("library.representation.Book.matchByName");
        q.setString("name", str);
        return q.list();
    }

    public List<Book> findByAuthorId(int id) {
        Query q = currentSession().getNamedQuery("library.representation.Book.findByAuthorId");
        q.setInteger("id", id);
        return q.list();
    }


}

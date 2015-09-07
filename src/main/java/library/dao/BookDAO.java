package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by raghunathan.mj on 01/09/15.
 */
public class BookDAO extends AbstractDAO<Book> {

    private final SessionFactory factory;

    public BookDAO(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

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

    public List<Book> findByAuthorName(String name) {
        Query q = currentSession().getNamedQuery("library.representation.Book.findByAuthorName");
        q.setString("name", name);
        return q.list();
    }

    public void deleteBook(Integer isbn) {
        Query children = currentSession().createSQLQuery("DELETE FROM book_author WHERE isbn = " + isbn);
        children.executeUpdate();
        Query q = currentSession().getNamedQuery("library.representation.Book.deleteByIsbn");
        q.setInteger("isbn", isbn);
        q.executeUpdate();
        return;
    }

    public Book exists(Integer isbn) {
        Session session = factory.openSession();
        Query q = session.getNamedQuery("library.representation.Book.getIsbnIfExists");
        q.setInteger("isbn", isbn);
        List<Book> books = q.list();
        session.close();
        if (books.size() > 0)
            return books.get(0);
        else
            return null;
    }
}

package library.dao;

import io.dropwizard.hibernate.AbstractDAO;
import library.representation.Author;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by raghunathan.mj on 01/09/15.
 */
public class AuthorDAO extends AbstractDAO<Author> {
    private final SessionFactory factory;
    public AuthorDAO(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    public Author findById(int id) {return get(id);}

    public Author create(Author author) {return persist(author);}

    public List<Author> findAll() {return list(namedQuery("library.representation.Author.findAll"));}

    public List<Author> findByName(String name) {
        Query q = currentSession().getNamedQuery("library.representation.Author.findByName");
        q.setString("name", name);
        List<Author> retVal = q.list();
        return retVal;
    }

    public void deleteAuthor(Integer id) {
        Query relatedBooks = currentSession().createSQLQuery("SELECT isbn FROM book_author WHERE id = " + id);
        List<Integer> relatedIsbn = relatedBooks.list();
        for (ListIterator<Integer> ite = relatedIsbn.listIterator(); ite.hasNext();) {
            Integer isbn = ite.next();
            Query deleteRelationTable = currentSession().createSQLQuery("DELETE FROM book_author WHERE isbn = " + isbn);
            deleteRelationTable.executeUpdate();
            Query deleteBook = currentSession().getNamedQuery("library.representation.Book.deleteByIsbn");
            deleteBook.setInteger("isbn", isbn);
            deleteBook.executeUpdate();
        }
        Query deleteAuthor = currentSession().getNamedQuery("library.representation.Author.deleteById");
        deleteAuthor.setInteger("id", id);
        deleteAuthor.executeUpdate();
        return;
    }

    public void modifyAuthor(Author author) {
        Query updateQuery = currentSession().getNamedQuery("library.representation.Author.modify");
        updateQuery.setInteger("id", author.getId());
        updateQuery.setString("email", author.getEmail());
        updateQuery.setString("name", author.getName());
        updateQuery.executeUpdate();
        return;
    }

    public Author exists(Integer id) {
        Session session = factory.openSession();
        Query exists = session.getNamedQuery("library.representation.Author.exists");
        exists.setInteger("id", id);
        List<Author> authors = exists.list();
        session.close();
        if (authors.size() > 0)
            return authors.get(0);
        else
            return null;
    }
}

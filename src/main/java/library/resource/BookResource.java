package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.BookDAO;
import library.representation.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by raghunathan.mj on 01/09/15.
 */

@Path("book")
public class BookResource {
    private final BookDAO bookDAO;

    public BookResource() {
        this.bookDAO = null;
    }

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GET
    @Path("get/{isbn}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("isbn") int isbn) {
        Book book = bookDAO.findById(isbn);
        return book;
    }

    @GET
    @Path("get-all")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        List<Book> allBooks = bookDAO.findAll();
        return allBooks;
    }

    @POST
    @Path("create")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(Book book) {
        return bookDAO.create(book);
    }

    @GET
    @Path("search/{isbn}/{name}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> search(@PathParam("name") String name, @PathParam("isbn") int isbn) {
        List<Book> books = new ArrayList<Book>();
        if (name != "undefined")
            books = bookDAO.matchByName(name);

        if (isbn < 0)
            return books;

        Book book = bookDAO.findById(isbn);
        if (books.size() > 0) {
            for (Iterator<Book> ite = books.listIterator(); ite.hasNext();) {
                if (ite.next().getIsbn() == book.getIsbn()) {
                    List<Book> retBook = new ArrayList<Book>();
                    retBook.add(book);
                    return retBook;
                }
            }
            return null;
        }

        else {
            List<Book> retBook = new ArrayList<Book>();
            retBook.add(book);
            return retBook;
        }

    }
}

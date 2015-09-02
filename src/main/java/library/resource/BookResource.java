package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.BookDAO;
import library.representation.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("create/{isbn}/{name}/{quantity}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(@PathParam("isbn") int isbn,
                           @PathParam("name") String name,
                           @PathParam("quantity") int quantity) {
        Book newBook = new Book(isbn, name, quantity);
        return bookDAO.create(newBook);
    }
}

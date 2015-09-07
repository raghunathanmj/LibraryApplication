package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.BookDAO;
import library.representation.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
        Book existingBook = bookDAO.findById(book.getIsbn());
        if (existingBook != null) {
            return new Book(null, null, null, null);
        }

        try  {
            return bookDAO.create(book);
        }
        catch (Exception e) {
            System.err.format("Hibernate Exception: %s%n", e);
        }
        return new Book(-1, null, null, null);
    }

    @PUT
    @Path("modify")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book modifyBook(Book book) {
        Book existingBook = bookDAO.exists(book.getIsbn());
        if (existingBook == null) {
            return new Book(null, null, null, null);
        }
        return bookDAO.create(book);
    }

    @DELETE
    @Path("delete/{isbn}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Book deleteBook(@PathParam("isbn") int isbn) {
        Book existingBook = bookDAO.exists(isbn);
        if (existingBook == null) {
            return new Book(null, null, null, null);
        }
        bookDAO.deleteBook(isbn);
        return existingBook;
    }

    @GET
    @Path("search/isbn/{isbn}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> searchByIsbn(@PathParam("isbn") int isbn) {
        Book book = bookDAO.findById(isbn);
        List<Book> retBook = new ArrayList<Book>();
        retBook.add(book);
        return retBook;
    }

    @GET
    @Path("search/name/{name}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> searchByName(@PathParam("name") String name) {
        List<Book> retBooks = new ArrayList<Book>();
        retBooks = bookDAO.matchByName(name);
        return retBooks;
    }

    @GET
    @Path("author/id/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> searchByAuthorId(@PathParam("id") int id) {
        List<Book> retBooks = bookDAO.findByAuthorId(id);
        return retBooks;
    }

    @GET
    @Path("author/name/{name}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> searchByAuthorName(@PathParam("name") String name) {
        List<Book> retBooks = bookDAO.findByAuthorName(name);
        return retBooks;
    }

}

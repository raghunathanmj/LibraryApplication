package library.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import library.dao.AuthorBookRelationDAO;
import library.dao.LibraryDAO;
import library.representation.Book;
import library.representation.PseudoBook;

@Path("/library")
public class LibraryResource {

    private final LibraryDAO bookDAO;
    private final AuthorBookRelationDAO authBookDAO;

    public LibraryResource() {
        bookDAO = null;
        authBookDAO = null;
    }

    public LibraryResource(LibraryDAO bookDAO, AuthorBookRelationDAO authBookDAO) {
        this.bookDAO = bookDAO;
        this.authBookDAO = authBookDAO;
    }

    @GET
    @Path("search/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookbyId(@PathParam("isbn") int isbn) {
        //GETTING INTO BROWSER
        //System.out.println("Inside the DB fn");
        Book book = bookDAO.getBookById(isbn);
        return Response.ok(book).build();
    }

    @GET
    @Path("show-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        List<Book> book = bookDAO.getAllBooks();
        return Response.ok(book).build();
    }

    @PUT
    @Path("update/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        System.out.println("Inside update POST");
        bookDAO.updateBook(book.getIsbn(), book.getTitle());
        return Response.ok(new Book(book.getIsbn(), book.getTitle())).build();
    }

    @DELETE
    @Path("del/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBook(Book book) {
        // delete the contact with the provided id
        bookDAO.deleteBook(book.getIsbn());
        bookDAO.adjustAuthBookForBookDeletion(book.getIsbn());
        return Response.noContent().build();
    }

    @POST
    @Path("pseudo-create/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void pseudoCreateBook(PseudoBook pBook) {
        bookDAO.createBook(pBook.getPseudoIsbn(), pBook.getPseudoTitle());
        int isbn = pBook.getPseudoIsbn();
        String[] authorList = pBook.getAuthorList().split(",");
        for (int i = 0; i < authorList.length; i++) {
            authBookDAO.newBookAuthors(pBook.getPseudoIsbn(), Integer.parseInt(authorList[i]));
        }
    }
}
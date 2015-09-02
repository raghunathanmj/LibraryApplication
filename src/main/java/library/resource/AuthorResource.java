package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.AuthorDAO;
import library.representation.Author;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("author")
public class AuthorResource {
    private final AuthorDAO authorDAO;

    public AuthorResource() {
        authorDAO = null;
    }

    public AuthorResource(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GET
    @Path("get/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Author getAuthorById(@PathParam("id") int id) {
        Author author = authorDAO.findById(id);
        return author;
    }

    @GET
    @Path("get-all")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors() {
        List<Author> allAuthors = authorDAO.findAll();
        return allAuthors;
    }

    @POST
    @Path("create/{id}/{name}/{email}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Author createAuthor(@PathParam("id") int id,
                               @PathParam("name") String name,
                               @PathParam("email") String email) {
        Author newAuthor = new Author(id, name, email);
        return authorDAO.create(newAuthor);
    }
}

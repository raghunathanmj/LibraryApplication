package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.AuthorDAO;
import library.representation.Author;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    @Path("create/")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Author createAuthor(Author author) {
        return authorDAO.create(author);
    }

    @GET
    @Path("search/id/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> searchById(@PathParam("id") Integer id) {
        Author author = authorDAO.findById(id);
        List<Author> retVal = new ArrayList<Author>();
        retVal.add(author);
        return retVal;
    }

    @GET
    @Path("search/name/{name}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> searchByName(@PathParam("name") String name) {
        List<Author> retVal = authorDAO.findByName(name);
        return retVal;
    }

    @DELETE
    @Path("delete/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Author deleteAuthor(@PathParam("id") Integer id) {
        Author existingAuthor = authorDAO.findById(id);
        if (existingAuthor == null) {
            Author error = new Author(null, null, null);
            return error;
        }
        authorDAO.deleteAuthor(id);
        return existingAuthor;
    }

    @PUT
    @Path("modify")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Author modifyAuthor(Author author) {
        Author existingAuthor = authorDAO.findById(author.getId());
        if (existingAuthor == null) {
            Author error = new Author(null, null, null);
            return error;
        }
        authorDAO.modifyAuthor(author);
        return author;
    }
}

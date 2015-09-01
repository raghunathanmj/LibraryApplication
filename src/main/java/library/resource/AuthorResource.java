package library.resource;

import library.dao.AuthorDAO;
import library.representation.Author;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by raghunathan.mj on 27/07/15.
 */
@Path("/author")
public class AuthorResource {
    private final AuthorDAO DAO;

    public AuthorResource() {DAO = null;}
    public AuthorResource(AuthorDAO dao) {DAO = dao;}

   @GET
    @Path("/view/{authorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAuthor(@PathParam("authorID") int authorID) {
        Author author = DAO.getAuthorById(authorID);
        return Response.ok(author).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuthor(Author author) throws URISyntaxException {
        int newAuthorId = DAO.createAuthor(author.getAuthorID(), author.getName());
        return Response.created(new URI(String.valueOf(newAuthorId))).build();
    }

    /*@POST
    @Path("/create")
    @Consumes(MediaType.TEXT_PLAIN)
    public void createAuthor(String s) throws URISyntaxException {
        JSONParser p = new JSONParser();
    }*/

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAuthor(Author author) {
        DAO.updateAuthor(author.getAuthorID(), author.getName());
        return Response.ok(new Author(author.getAuthorID(), author.getName())).build();
    }

    @DELETE
    @Path("del/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(Author author) {
        DAO.deleteAuthor(author.getAuthorID());
        DAO.adjustAuthBookForAuthDeletion(author.getAuthorID());
        DAO.adjustBookForAuthDeletion();
        return Response.noContent().build();
    }

}

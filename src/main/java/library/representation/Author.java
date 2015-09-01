package library.representation;

/**
 * Created by raghunathan.mj on 27/07/15.
 */
public class Author {
    private final int authorID;
    private final String name;

    public Author() {
        authorID = 0;
        name = "unknown";
    }

    public Author (int authorID,String name) {
        this.authorID =authorID;
        this.name = name;
    }

    public int getAuthorID() {return authorID;}
    public String getName() {return  name;}
}

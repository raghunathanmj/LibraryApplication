package library.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghunathan.mj on 27/07/15.
 */
public class PseudoBook {
    private final int pseudoIsbn;
    private final String pseudoTitle;
    private final String authorList;

    public PseudoBook() {
        pseudoIsbn = 0;
        pseudoTitle = "";
        authorList = null;
    }

    public PseudoBook(int pseudoIsbn, String pseudoTitle, String authorList) {
        this.pseudoIsbn = pseudoIsbn;
        this.pseudoTitle = pseudoTitle;
        this.authorList = authorList;
    }

    public int getPseudoIsbn() {return pseudoIsbn;}
    public String getPseudoTitle() {return pseudoTitle;}
    public String getAuthorList() {return authorList;}
}

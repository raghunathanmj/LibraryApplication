package library.representation;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghunathan.mj on 27/07/15.
 */

@Entity
@Table(name = "Author")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "library.representation.Author.findAll",
                query = "SELECT * from Author",
                resultClass = Author.class
        )
})
@Getter
public class Author {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "authorName", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(
            mappedBy = "authors",
            cascade = {CascadeType.REMOVE},
            targetEntity = library.representation.Book.class,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<Book>();

    public Author() {
        id = null;
        name = email = null;
    }

    public Author(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

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
        ),
        @NamedNativeQuery(
                name = "library.representation.Author.deleteById",
                query = "DELETE FROM Author WHERE id = :id",
                resultClass = Author.class
        ),
        @NamedNativeQuery(
                name = "library.representation.Author.findByName",
                query = "SELECT * FROM Author WHERE authorName LIKE CONCAT('%', :name, '%')",
                resultClass = Author.class
        ),
        @NamedNativeQuery(
                name = "library.representation.Author.modify",
                query = "UPDATE Author SET authorName = :name, email = :email WHERE id = :id",
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
            targetEntity = library.representation.Book.class
    )
    private List<Book> books = new ArrayList<Book>();

    public Author() {
        id = null;
        name = email = null;
    }

    public Author(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

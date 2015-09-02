package library.representation;

import lombok.Getter;

import javax.persistence.*;

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
    private final Integer id;

    @Column(name = "authorName", nullable = false)
    private final String name;

    @Column(name = "email", nullable = false)
    private final String email;

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

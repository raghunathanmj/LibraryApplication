package library.representation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Created by raghunathan.mj on 22/07/15.
 */

@Entity
@Table(name = "Book")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "library.representation.Book.findAll",
                query = "SELECT * FROM Book",
                resultClass = Book.class
        ),
        @NamedNativeQuery(
                name = "library.representation.Book.findByIsbn",
                query = "SELECT * FROM Book WHERE isbn = :isbn",
                resultClass = Book.class
        ),
        @NamedNativeQuery(
                name = "library.representation.Book.matchByName",
                query = "SELECT * FROM Book WHERE name LIKE CONCAT('%',:name,'%')",
                resultClass = Book.class
        ),
        @NamedNativeQuery(
                name = "library.representation.Book.findByAuthorId",
                query = "SELECT * FROM Book WHERE isbn in (SELECT isbn FROM book_author where id = :id)",
                resultClass = Book.class
        )
})
@Getter
@Setter
@javax.persistence.SequenceGenerator(
        name = "SEQ_STORE",
        sequenceName = "my_sequence",
        allocationSize = 20
)
public class Book implements Serializable{
    @Id
    @Column(name = "isbn", nullable = false)
    private int isbn; //Unique

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToMany(
            targetEntity = library.representation.Author.class,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Author> authors = new ArrayList<Author>();

    public Book() {
        isbn = quantity = -1;
        name = null;
        authors = null;
    }

    public Book(int isbn, String name, int quantity, List<Author> authors) {
        this.isbn = isbn;
        this.name = name;
        this.quantity = quantity;
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Book)) return false;
        else if (this == o) return true;

        else {
            Book that = (Book) o;
            return Objects.equals(this.isbn, that.isbn) &&
                    Objects.equals(this.name, that.name) &&
                    Objects.equals(this.quantity, that.quantity);
        }
    }
}


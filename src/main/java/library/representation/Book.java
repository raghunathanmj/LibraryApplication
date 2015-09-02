package library.representation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by raghunathan.mj on 22/07/15.
 */

@Entity
@Table(name = "Book")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "library.representation.Book.findAll",
                query = "SELECT * from Book",
                resultClass = Book.class
        )
})
@Getter
public class Book {
    @Id
    @Column(name = "isbn", nullable = false)
    private final Integer isbn; //Unique

    @Column(name = "name", nullable = false)
    private final String name;

    @Column(name = "quantity", nullable = false)
    private final Integer quantity;

    public Book() {
        isbn = quantity = null;
        name = null;
    }

    public Book(int isbn, String name, int quantity) {
        this.isbn = isbn;
        this.name = name;
        this.quantity = quantity;
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


package library.representation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
/**
 * Created by raghunathan.mj on 19/08/15.
 */


@Entity
@Table(name = "User")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "library.representation.User.findAll",
                query = "Select * FROM User",
                resultClass = User.class
        )
})
@Getter
public class User {
    @Id
    @Column(name = "username", nullable = false)
    private final String username;

    @Column(name = "userPassword", nullable = false)
    private final String password;

    @Column(name = "personName", nullable = false)
    private final String name;

    @Column(name = "email", nullable = false)
    private final String email;

    public User() {
        this.username = this.password = this.name = this.email = null;
    }

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        else if (this == o) return true;

        final User that = (User) o;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.email, that.email);

    }
}

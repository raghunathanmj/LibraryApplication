package library.representation;

import javax.persistence.*;
import java.util.Objects;
/**
 * Created by raghunathan.mj on 19/08/15.
 */


@Entity
@Table(name = "LibraryUser")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "library.representation.UserHibernate.findAll",
                query = "Select * FROM LibraryUser",
                resultClass = UserHibernate.class
        )
})
public class UserHibernate {
    @Id
    @Column(name = "username", nullable = false)
    private final String username;

    @Column(name = "userPassword", nullable = false)
    private final String password;

    @Column(name = "personName", nullable = false)
    private final String name;

    public UserHibernate() {
        this.username = this.password = this.name = null;
    }

    public UserHibernate(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof UserHibernate)) return false;

        final UserHibernate that = (UserHibernate) o;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.name, that.name);

    }

    public String getPassword() {return password;}
    public String getUsername() {return username;}
    public String getName() {return name;}

}

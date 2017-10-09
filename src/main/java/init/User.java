package init;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User
{
    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    protected User() {}

    public User(String firstName, String lastName, String login, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString(){
        return String.format(
            "User[id=%d, firstName='%s', lastName='%s', login='%s', email='%s', role='%s']",
            id, firstName, lastName, login, email, role
        );
    }

    public Object toArray() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(firstName);
        list.add(lastName);
        list.add(login);
        list.add(email);
        list.add(email);
        list.add(role);
        return list;
    }
}

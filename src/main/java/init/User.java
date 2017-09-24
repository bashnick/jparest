package init;

import javax.persistence.*;

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

    @Column(name = "role")
    private String role;

    protected User() {}

    public User(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public String toString(){
        return String.format(
            "User[id=%d, firstName='%s', lastName='%s', role='%s']",
            id, firstName, lastName, role
        );
    }
}

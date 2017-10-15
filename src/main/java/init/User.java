package init;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
public class User
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

    protected User() {}

    public User(String firstname, String lastname, String username, String password, String email, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString(){
        return String.format(
            "User[id=%d, firstname='%s', lastname='%s', login='%s', password='%s', email='%s', role='%s']",
            id, firstname, lastname, login, password, email, role
        );
    }

    //setters
    public void setFirstname(String firstname){ this.firstname = firstname; }
    public void setLastname(String lastname){ this.lastname = lastname; }
    public void setLogin(String username){ this.login = username; }
    public void setPassword(String password){ this.password = password; }
    public void setEmail(String email){ this.email = email; }
    public void setRole(String role){ this.role = role; }

    //getters
    public Long getId() { return this.id; }
    public String getFirstname(){ return this.firstname; }
    public String getLastname(){ return this.lastname; }
    public String getLogin(){ return this.login; }
    public String getPassword(){ return this.password; }
    public String getEmail(){ return this.email; }
    public String getRole(){ return this.role; }
}

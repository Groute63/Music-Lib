package company.entityclass.role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "login",unique=true, nullable = false)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "myrole")
    @Enumerated(EnumType.STRING)
    private MyRole role;

    public void setRole(MyRole role) {
        this.role = role;
    }

    private int acc = 1; //todo заменить
    private boolean activ = true;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", acc=" + acc +
                ", activ=" + activ +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){

    }
}

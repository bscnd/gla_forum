package beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Bean représentant un utilisateur
 */
public class UserProfile {

    private Long id;
    private String username;
    private String password;
    private String role;
    private Timestamp created;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}

package beans;

import java.sql.Timestamp;

/**
 * Bean représentant un utilisateur
 */
public class UserProfile {

    private Long id;
    private String username;
    private String password;
    private String salt;
    private String role;
    private Timestamp created;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt(){
        return salt;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}

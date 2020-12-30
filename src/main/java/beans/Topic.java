package beans;

public class Topic {
    private Long id;
    private String topicname;
    private Long createur;


    public long getId() {
        return id;
    }

    public String getTopicname() {
        return topicname;
    }

    public long getCreateur() {
        return createur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public void setCreateur(long createur) {
        this.createur = createur;
    }
}

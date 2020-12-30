package beans;

public class Topic {
    private Long id;
    private String topicname;
    private int createur;


    public long getId() {
        return id;
    }

    public String getTopicname() {
        return topicname;
    }

    public int getCreateur() {
        return createur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public void setCreateur(int createur) {
        this.createur = createur;
    }
}

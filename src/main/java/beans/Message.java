package beans;

public class Message {
    private Long id;
    private String auteur;
    private String contenu;
    private int topic;

    public Long getId() {
        return id;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public int getTopic() {
        return topic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }
}

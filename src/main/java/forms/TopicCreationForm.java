package forms;

import beans.Topic;
import dao.DAOException;
import dao.DAOTopic;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class TopicCreationForm {

    public static final String CHAMP_TOPICNAME = "topicname";

    String resultat;
    Map<String, String> erreurs = new HashMap<>();
    private DAOTopic daoTopic;

    public TopicCreationForm(DAOTopic daoTopic){
        this.daoTopic = daoTopic;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public Topic creerTopic( HttpServletRequest request ) {
        String topicname = getValeurChamp( request, CHAMP_TOPICNAME );

        Topic topic = new Topic();
        try {
            traiterTopicname( topicname, topic );
            int createur = 6;
            topic.setCreateur(createur);

            if ( erreurs.isEmpty() ) {
                // On créé un nouvea topic seulement si son nom est valide.
                daoTopic.creer( topic );
                resultat = "Nouveau topic créé.";
            } else {
                resultat = "Échec de la création d'un nouveau topic. Des erreurs sont survenues.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de la création d'un nouveau topic : une erreur imprévue est survenue.";
            resultat = "name : " + topic.getTopicname() + "\n" + "createur : " + topic.getCreateur();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topic;
    }

    private void traiterTopicname( String topicname, Topic topic) throws Exception {
        try {
            validationTopicname( topicname );
        } catch (FormValidationException e ) {
            setErreur( CHAMP_TOPICNAME, e.getMessage() );
        }
        topic.setTopicname( topicname );
    }



    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /**
     * Méthode utilitaire
     * @param request est la requête concernée
     * @param nomChamp est le champ à vérifier
     * @return null si un champ est vide. Son contenu sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }


    private void validationTopicname(String topicname)throws Exception{
        if (topicname != null){
            if (topicname.length() < 5){
                throw new FormValidationException("Le nom du topic doit faire au moins 5 caractères");
            }
        }
    }
}

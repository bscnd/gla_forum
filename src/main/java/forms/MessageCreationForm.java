package forms;

import beans.Message;
import beans.Topic;
import beans.UserProfile;
import dao.DAOException;
import dao.DAOMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MessageCreationForm {
    public static final String CHAMP_MESSAGE_CONTENU = "contenu";
    private static final String SESSION_USER = "sessionUtilisateur";


    String resultat;
    Map<String, String> erreurs = new HashMap<>();
    private DAOMessage daoMessage;

    public MessageCreationForm(DAOMessage daoMessage){
        this.daoMessage = daoMessage;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public Message creerMessage(HttpServletRequest request ) {
        String contenu = getValeurChamp( request, CHAMP_MESSAGE_CONTENU );

        Message message = new Message();

        try {
            traiterContenu( contenu, message );

            HttpSession session = request.getSession();
            UserProfile auteur = (UserProfile) session.getAttribute(SESSION_USER);
            message.setAuteur(auteur.getUsername());
            message.setTopic(Long.parseLong(request.getParameter("topicid")));

            if ( erreurs.isEmpty() ) {
                // On créé un nouvea topic seulement si son nom est valide.
                daoMessage.creer( message );
                resultat = "Nouveau message créé.";
            } else {
                resultat = "Échec de la création d'un nouveau message. Des erreurs sont survenues.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de la création d'un nouveau message : une erreur imprévue est survenue.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private void traiterContenu( String contenu, Message message) throws Exception {
        try {
            validationContenu( contenu );
        } catch (FormValidationException e ) {
            setErreur( CHAMP_MESSAGE_CONTENU, e.getMessage() );
        }
        message.setContenu( contenu );
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

    private void validationContenu(String contenu)throws Exception{
        if (contenu == null){
            throw new FormValidationException("Le message saisi est vide.");
        }
    }

}

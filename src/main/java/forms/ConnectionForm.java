package forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import beans.UserProfile;

public final class ConnectionForm {
    private static final String CHAMP_USERNAME  = "username";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public UserProfile connecterUtilisateur( HttpServletRequest request ) {
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        UserProfile utilisateur = new UserProfile();

        try {
            validationUsername( username );
        } catch ( Exception e ) {
            setErreur( CHAMP_USERNAME, e.getMessage() );
        }
        utilisateur.setUsername( username );

        /* Validation du champ mot de passe. */
        try {
            validationMdp( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setPassword( motDePasse );

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationUsername( String username ) throws Exception {
        if ( username != null ) {
            if (username.length() < 3){
                throw new Exception( "Saisissez un nom d'utilisateur valide" );
            }
        } else {
            throw new Exception("Saisissez votre nom d'utilisateur.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMdp( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
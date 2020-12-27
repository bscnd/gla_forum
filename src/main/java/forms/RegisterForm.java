package forms;

import beans.UserProfile;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class RegisterForm {

    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";
    public static final String CHAMP_CONF = "confirmation";

    String resultat;
    Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public UserProfile inscrireUtilisateur( HttpServletRequest request ) {
        String password = getValeurChamp( request, CHAMP_PASSWORD );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String username = getValeurChamp( request, CHAMP_USERNAME );

        UserProfile utilisateur = new UserProfile();

        try {
            validationMdp( password, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASSWORD, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        utilisateur.setPassword( password );

        try {
            assert username != null;
            validationUsername( username );
        } catch ( Exception e ) {
            setErreur( CHAMP_USERNAME, e.getMessage() );
        }
        utilisateur.setUsername( username );

        if ( erreurs.isEmpty() ) {
            resultat = "Inscription réussie";
        } else {
            resultat = "Echec de l'inscription";
        }
        return utilisateur;
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

    /**
     * Une exception est levée si les mots de passe saisis dans les champs password et confirmation
     * ne sont pas identiques et/ou ne font pas au moins 5 caractères de long.
     *
     * @param password le mot de passe de l'utilisateur
     * @param confirmation la confirmation
     * @throws Exception levée en cas d'incohérence
     */
    private void validationMdp(String password, String confirmation) throws Exception{
        // TODO : Renforcer les prérequis de sécurité sur les mots de passe
        if (password != null && confirmation != null){
            if (!password.equals(confirmation)){
                throw new Exception("les mots de passe saisis ne correspondent pas.");
            } else if (password.trim().length() < 5){
                throw new Exception("Les mots de passe doivent contenir au moins 5 caractères");
            }
        } else {
            throw new Exception("Veuillez saisir un mot de passe et le confirmer.");
        }
    }

    private void validationUsername(String username)throws Exception{
        if (username.trim().length() < 3){
            throw new Exception("Choisissez un nom d'utilisateur de 3 caractères ou plus.");
        }
    }
}

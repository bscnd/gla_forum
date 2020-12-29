package forms;

import beans.UserProfile;
import dao.DAOException;
import dao.DAOUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginForm {
    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";

    private Map<String, String> erreurs = new HashMap<>();
    private String resultat;
    private DAOUser daoUser;

    public String getResultat(){
        return resultat;
    }

    public Map<String, String> getErreurs(){
        return erreurs;
    }

    public UserProfile connecterUtilisateur(HttpServletRequest request ) {
        String password = getValeurChamp(request, CHAMP_PASSWORD);
        String username = getValeurChamp(request, CHAMP_USERNAME);

        UserProfile utilisateur = new UserProfile();

        try {
            // TODO : Cas ou utilisateur = null ?
            utilisateur = daoUser.trouver(username);
        } catch (DAOException e) {
            resultat = "Impossible de se connecter. Utilisateur inexistant.";
        }

        String databasePassword = utilisateur.getPassword();

        return utilisateur;
    }


    private Boolean comparePasswords(String inputPassword, String databasePassword ){

        return false;
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
}

package forms;

import beans.UserProfile;
import dao.DAOException;
import dao.DAOUser;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class LoginForm {
    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";

    private Map<String, String> erreurs = new HashMap<>();
    private String resultat;
    private DAOUser daoUser;

    public LoginForm (DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    public String getResultat(){
        return resultat;
    }

    public Map<String, String> getErreurs(){
        return erreurs;
    }
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


    public UserProfile connecterUtilisateur(HttpServletRequest request ) {
        String password = getValeurChamp(request, CHAMP_PASSWORD);
        String username = getValeurChamp(request, CHAMP_USERNAME);
        UserProfile utilisateur = new UserProfile();

        try {
            traiterUser(username);

            if (erreurs.isEmpty()){
                utilisateur = daoUser.trouver(username);
                Base64.Decoder decoder = Base64.getDecoder();
                String databasePassword = utilisateur.getPassword();
                String databaseSalt = utilisateur.getSalt();
                byte[] salt = decoder.decode(databaseSalt);
                assert password != null;
                traiterPassword(password, databasePassword, salt);
                resultat = "Bienvenue !";
            }else{
                resultat = "Echec de l'authentification";
            }
        } catch (DAOException e) {
            resultat = "Echec de l'authentification.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    private void traiterUser (String username) throws Exception{
        try {
            validerUser(username);
        } catch (FormValidationException e){
            setErreur(CHAMP_USERNAME, e.getMessage());
        }
    }

    private void validerUser (String username) throws Exception {
        if(daoUser.trouver(username) == null){
            throw new DAOException("Utilisateur introuvable.");
        }
    }

    private void traiterPassword (String inputPassword, String databasePassword, byte[] salt) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();

        // Hash du mot de passe saisi par l'utilisateur pour comparaison
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        inputPassword = encoder.encodeToString(hash);

        try {
            comparePasswords( inputPassword, databasePassword );
        } catch (FormValidationException e ) {
            setErreur(CHAMP_PASSWORD, e.getMessage());
        }
    }


    private void comparePasswords(String inputPassword, String databasePassword) throws Exception{
        if (inputPassword != null && databasePassword != null){
            if (!databasePassword.equals(inputPassword)){
                throw new Exception("Mauvais mot de passe");
            }
        } else {
            throw new Exception("Saisissez un mot de passe");
        }
    }

    /**
     * Méthode utilitaire pour récupérer la valeur d'un champ.
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

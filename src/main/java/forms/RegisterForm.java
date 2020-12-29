package forms;

import beans.UserProfile;
import dao.DAOException;
import dao.DAOUser;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;

public class RegisterForm {

    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";
    public static final String CHAMP_CONF = "confirmation";
    public static final String CHAMP_ROLE = "role";

    String resultat;
    Map<String, String> erreurs = new HashMap<>();
    private DAOUser daoUser;

    public RegisterForm (DAOUser daoUser){
        this.daoUser = daoUser;
    }

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
        String role = "utilisateur";

        UserProfile utilisateur = new UserProfile();

        try {
            traiterUsername( username, utilisateur );
            traiterPassword( password, confirmation, utilisateur );
            traiterRole( role, utilisateur );

            if ( erreurs.isEmpty() ) {
                // On inscrit l'utilisateur dans le BDD uniquement si tous les champs sont valides
                daoUser.creer( utilisateur );
                resultat = "Succès de l'inscription.";
            } else {
                resultat = "Échec de l'inscription.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return utilisateur;
    }

    private void traiterUsername( String username, UserProfile utilisateur ) throws Exception {
        try {
            validationUsername( username );
        } catch (FormValidationException e ) {
            setErreur( CHAMP_USERNAME, e.getMessage() );
        }
        utilisateur.setUsername( username );
    }

    private void traiterRole (String role, UserProfile utilisateur) throws Exception {
        try {
            validationRole(role);
        } catch (FormValidationException e){
            setErreur(CHAMP_ROLE, e.getMessage());
        }
        utilisateur.setRole(role);
    }


    /**
     * @param motDePasse est une string représentant le mot de passe de l'utilisateur en clair
     * @param confirmation est le champ de confirmation de saisie du mot de passe
     * @param utilisateur est le bean associé à la saisie
     * @throws Exception en cas de soucis
     */
    private void traiterPassword( String motDePasse, String confirmation, UserProfile utilisateur ) throws Exception {
        try {
            validationMdp( motDePasse, confirmation );
        } catch (FormValidationException e ) {
            setErreur( CHAMP_PASSWORD, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        Base64.Encoder encoder = Base64.getEncoder();

        utilisateur.setPassword(encoder.encodeToString(hash));
        utilisateur.setSalt(encoder.encodeToString(salt));
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
        if (username != null){
            if (username.length() < 3){
                throw new FormValidationException("Choisissez un nom d'utilisateur d'au moins 3 caractères");
            } else if (daoUser.trouver(username) != null){
                throw new FormValidationException("Ce nom d'utilisateur est déjà pris. Choisissez-en un autre.");
            }
        } else {
            throw new FormValidationException("Veuillez saisir un nom d'utilsateur.");
        }
    }

    private void validationRole(String role)throws Exception{
        if (role != null){
            if (!role.matches("utilisateur|administrateur|moderateur|invite")){
                throw new FormValidationException("Le rôle choisi est invalide");
            }
        } else {
            throw new FormValidationException("Aucun rôle choisi pour cet utilisateur");
        }
    }
}

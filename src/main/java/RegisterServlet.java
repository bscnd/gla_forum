import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet (name = "AuthServlet")
public class RegisterServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/register.jsp";
    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";
    public static final String CHAMP_CONF = "confirmation";


    // TODO : Protected ou Public ?
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String resultat;
        Map<String, String> erreurs = new HashMap<>();

        String username = request.getParameter( CHAMP_USERNAME );
        String password = request.getParameter( CHAMP_PASSWORD );
        String confirmation = request.getParameter( CHAMP_CONF );

        // Validation du champ mot de passe
        try {
            validationMdp(password, confirmation);
        } catch (Exception e) {
            erreurs.put(CHAMP_PASSWORD, e.getMessage());
        }

        // Validation du champ username
        try {
            validationUsername(username);
        } catch (Exception e) {
            erreurs.put(CHAMP_USERNAME, e.getMessage());
        }

        if (erreurs.isEmpty()){
            resultat = "Inscription réussie";
        } else {
            resultat = "Echec de l'inscription";
        }

        request.setAttribute("erreurs", erreurs);
        request.setAttribute("resultat", resultat);

        // Transmission de la paire d'objets request / response à la JSP "VUE"
        request.getRequestDispatcher(VUE).forward(request, response);
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

package servlets;

import beans.UserProfile;
import dao.DAOFactory;
import dao.DAOUser;
import forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "servlets.AccueilServlet")
public class AccueilServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/threads.jsp";
    private static final String SESSION_USER = "sessionUtilisateur";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private DAOUser daoUser;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur (dans init pour éviter qu'une instance ne soit
         * créée à chaque requête reçue. */
        this.daoUser = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // Restauration du contexte
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}

package servlets;

import beans.UserProfile;
import forms.RegisterForm;
import dao.DAOUser;
import dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet (name = "servlets.AuthServlet")
public class RegisterServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/register.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    private DAOUser daoUser;

    /**
     * Actions à effectuer 1 seule fois à la création de cette servlet
     * @throws ServletException si il y a un problème avec ces actions
     */
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur (dans init pour éviter qu'une instance ne soit
        * créée à chaque requête reçue. */
        this.daoUser = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    // TODO : Protected ou Public ?
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        RegisterForm form = new RegisterForm(daoUser);
        UserProfile utilisateur = form.inscrireUtilisateur(request);
        request.setAttribute("form", form);
        request.setAttribute("utilisateur", utilisateur);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

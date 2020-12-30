package servlets;

import beans.Topic;
import dao.DAOFactory;
import dao.DAOTopic;
import forms.TopicCreationForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlets.AccueilServlet")
public class TopicCreationServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/topicCreation.jsp";
    private static final String SESSION_USER = "sessionUtilisateur";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private DAOTopic daoTopic;

    // TODO : Besoin dans chaque servlet qui nécessite de manipuler des données ?
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur (dans init pour éviter qu'une instance ne soit
         * créée à chaque requête reçue. */
        this.daoTopic = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopicDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // Restauration du contexte
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TopicCreationForm form = new TopicCreationForm(daoTopic);
        Topic topic = form.creerTopic(request);
        request.setAttribute("form", form);
        request.setAttribute("topic", topic);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


}

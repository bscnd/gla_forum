package servlets;

import beans.Topic;
import dao.DAOFactory;
import dao.DAOTopic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "servlets.AccueilServlet")
public class AccueilServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/threads.jsp";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String ATT_TOPIC_LIST = "allTopics";
    private DAOTopic daoTopic;

    public void init() throws ServletException {
        this.daoTopic = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopicDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Récupération des topics et ajout à la requête
        Set<Topic> allTopics = daoTopic.getAllTopics();
        request.setAttribute(ATT_TOPIC_LIST, allTopics.toArray());

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

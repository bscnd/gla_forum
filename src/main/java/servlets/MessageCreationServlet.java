package servlets;

import beans.Message;
import beans.Topic;
import dao.DAOFactory;
import dao.DAOMessage;
import dao.DAOTopic;
import forms.MessageCreationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageCreationServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/messageCreation.jsp";
    private static final String SESSION_USER = "sessionUtilisateur";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String CONCERNED_TOPIC_ID = "topicId";
    private DAOMessage daoMessage;
    private DAOTopic daoTopic;

    public void init() {
        this.daoMessage = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
        this.daoTopic = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopicDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get topic id in URL
        // TODO : Encode avec ça ?
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Long topicic = Long.parseLong(request.getParameter("topicid"));
        request.setAttribute(CONCERNED_TOPIC_ID, topicic);

        // Restauration du contexte
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long topicic = Long.parseLong(request.getParameter("topicid"));
        request.setAttribute(CONCERNED_TOPIC_ID, topicic);


        // TODO : ICI : Besoin de récupérer l'id de l'url dans le code java
        MessageCreationForm form = new MessageCreationForm(daoMessage);
        Message message = form.creerMessage(request);
        request.setAttribute("form", form);
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

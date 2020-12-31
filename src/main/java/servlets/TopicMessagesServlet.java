package servlets;

import beans.Message;
import beans.Topic;
import dao.DAOFactory;
import dao.DAOMessage;
import dao.DAOTopic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class TopicMessagesServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/thread.jsp";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String CONCERNED_TOPIC = "concernedTopic";
    private static final String TOPIC_MESSAGES = "topicMessages";
    private DAOTopic daoTopic;
    private DAOMessage daoMessage;

    public void init() {
        this.daoTopic = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopicDao();
        this.daoMessage = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pour utilisation directe dans la jsp
        Long topicId = Long.parseLong(request.getParameter("topicid"));
        Topic topic = daoTopic.getTopicById(topicId);
        Set<Message> threadMessages = daoMessage.getThreadMessages(topicId);
        // Impossible de traiter des sets -> conversion en tableau
        request.setAttribute(TOPIC_MESSAGES, threadMessages.toArray());
        request.setAttribute(CONCERNED_TOPIC, topic);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
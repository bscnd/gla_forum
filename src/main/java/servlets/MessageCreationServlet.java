package servlets;

import beans.Message;
import dao.DAOFactory;
import dao.DAOMessage;
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
    private DAOMessage daoMessage;

    public void init() throws ServletException {
        this.daoMessage = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // Restauration du contexte
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageCreationForm form = new MessageCreationForm(daoMessage);
        Message message = form.creerMessage(request);
        request.setAttribute("form", form);
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

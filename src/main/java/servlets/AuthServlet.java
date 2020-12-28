package servlets;

import beans.UserProfile;
import forms.ConnectionForm;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet (name = "servlets.AuthServlet")
public class AuthServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/login.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionForm form = new ConnectionForm();

        UserProfile utilisateur = form.connecterUtilisateur(request);
        HttpSession session = request.getSession();

        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( "sessionUtilisateur", utilisateur );
        } else {
            session.setAttribute( "utilisateur", null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( "form", form );
        request.setAttribute( "utilisateur", utilisateur );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }


}

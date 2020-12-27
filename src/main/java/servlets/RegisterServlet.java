package servlets;

import beans.UserProfile;
import forms.RegisterForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet (name = "servlets.AuthServlet")
public class RegisterServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/jsp/register.jsp";

    // TODO : Protected ou Public ?
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        RegisterForm form = new RegisterForm();
        UserProfile utilisateur = form.inscrireUtilisateur(request);
        request.setAttribute("form", form);
        request.setAttribute("utilisateur", utilisateur);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

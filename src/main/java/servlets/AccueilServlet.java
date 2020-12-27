package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.AccueilServlet")
public class AccueilServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Question > seulement doGet dans doPost car on ne fait rien d'autre que demander une autre page d'ici ?
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        ServletOutputStream out = response.getOutputStream();

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");

        if (name != null){
            out.println("Bonjour, " + name + " !");
        } else {
            out.println("Utilisateur inconnu !");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(request, response);



    }
}

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet (name = "AuthServlet")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();

        String username = request.getParameter("login");
        String password = request.getParameter("password");

        if ("dorian".equals(username) && "password".equals(password)){
            out.println("Welcome"+username);
        }
        else {
            out.println("AUTH KO");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

        /*
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Error("j'aime le viande",e);
        }
        System.out.println("Driver loaded");

        // Try to connect
        Connection connection = null;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://db:3306/testdb1", "testuser", "root");
        } catch (SQLException throwables) {
            throw new Error("j'aime le poulet",throwables);
        }

        System.out.println("It works!");

        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new Error("j'aime le poisson",throwables);
        }
         */
    }
}

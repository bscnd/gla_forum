package dao;

import beans.UserProfile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;

public class DAOConnection {

    public UserProfile getUserByLogin(String username){
        Connection connection = null;
        try {
            // TODO : username + password en clair dans le code ?
            connection = DriverManager.getConnection
                    ("jdbc:mysql://db:3306/forum", "testuser", "root");
        } catch (SQLException throwables) {
            throw new Error("Impossible de se connecter à la base de données",throwables);
        }
        System.out.println("Connexion à la base réussie !");

        UserProfile user = null;

        // Hash /w SHA256
        String password = "abc";
        // MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));


        // TODO : Traitement pour récupérer utilisateur dans la BDD

        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new Error("Impossible de fermer la connexion à la base de données",throwables);
        }

        return user;
    }
}

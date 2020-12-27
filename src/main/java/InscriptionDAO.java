import beans.UserProfile;

import java.sql.*;

// TODO : Objets inaccessibles ? https://bit.ly/34MCsXs

public class InscriptionDAO {
    // TODO : Quel paramètre pour la méthode insert ?
    // TODO : Cette classe doti-elle être un servlet ?

    public void insert(UserProfile user){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://db:3306/forum", "testuser", "root");
        } catch (SQLException throwables) {
            throw new Error("Impossible de se connecter à la base de données",throwables);
        }
        // TODO : Quel algorithme de hachage ?

        // Exemple de requête
        String query = "INSERT INTO users (username, password, role, created) VALUES (user1, pass1, administrateur, date);";

        System.out.println("Connexion à la base réussie !");


        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new Error("Impossible de fermer la connexion à la base de données",throwables);
        }
    }
}

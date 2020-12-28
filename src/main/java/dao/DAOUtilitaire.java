package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {

    public DAOUtilitaire() {
    }

    /**
     * @param resultSet est le resultset à fermer
     */
    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /**
     * @param statement est le statement à fermer
     */
    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /**
     * @param connexion est la connexion à fermer
     */
    public static void fermetureSilencieuse( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /**
     * @param statement à fermer
     * @param connexion à fermer
     */
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /**
     * @param resultSet à fermer
     * @param statement à fermer
     * @param connexion à fermer
     */
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /**
     * Méthode préparant une requête SQL
     * @param connexion issue de la DAOFactory
     * @param sql est une chaine contenant la requête SQL
     * @param returnGeneratedKeys est un booléen indiquant s'il faut retourner des valeurs auto-générées
     * @param objets de taille variable représentant les paramètres de la requête
     * @return une requête préparée
     * @throws SQLException si la requête est invalide
     */
    public static PreparedStatement initRequest(Connection connexion, String sql, boolean returnGeneratedKeys, Object... /* varargs ~= tableau */ objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }

}

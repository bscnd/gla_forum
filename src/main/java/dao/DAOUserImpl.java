package dao;

import beans.UserProfile;
import java.sql.*;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initRequest;

public class DAOUserImpl implements DAOUser{

    private DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (username, password, salt, role, created) VALUES (?, ?, ?, ?, NOW())";

    DAOUserImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    /**
     * Méthode ajoutant un utilisateur dans la base de données.
     * @param utilisateur à insérer dans la base de données
     * @throws DAOException si une erreur survient avec le DAO
     */
    @Override
    public void creer(UserProfile utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequest( connexion, SQL_INSERT_USER, true, utilisateur.getUsername(),
                                                utilisateur.getPassword(), utilisateur.getSalt(), utilisateur.getRole());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /**
     * Méthode peuplant un bean utilsateur en fonction d'une recherche en base de données.
     * @param username de l'utilisateur à trouver
     * @return un bean utilisateur peuplé avec les données de l'utilisateur associé au @param username
     * @throws DAOException si une erreur liée au DAO survient
     */
    @Override
    public UserProfile trouver(String username) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserProfile utilisateur = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequest( connexion, SQL_SELECT_PAR_USERNAME, false, username );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                utilisateur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
    }

    /**
     * Méthode faisant correspondra la ligne de la table utilisateur et un bean UserProfile
     * @param resultSet est le résultat de la requête SQL
     * @return un bean utilisateur peuplé
     * @throws SQLException si la requête est invalide
     */
    private static UserProfile map( ResultSet resultSet ) throws SQLException {
        UserProfile utilisateur = new UserProfile();
        utilisateur.setId( resultSet.getLong( "id" ) );
        utilisateur.setUsername( resultSet.getString( "username" ) );
        utilisateur.setPassword( resultSet.getString( "password" ) );
        utilisateur.setCreated( resultSet.getTimestamp( "created" ) );
        utilisateur.setRole( resultSet.getString( "role" ) );
        utilisateur.setSalt( resultSet.getString( "salt" ) );
        return utilisateur;
    }
}

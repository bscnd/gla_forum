package dao;

import beans.Topic;
import beans.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initRequest;

public class DAOTopicImpl implements DAOTopic {

    private DAOFactory daoFactory;
    private static final String SQL_INSERT_THREAD = "INSERT INTO threads (nom, createur) VALUES (?, ?)";

    DAOTopicImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Topic topic) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequest( connexion, SQL_INSERT_THREAD, true, topic.getTopicname(), topic.getCreateur());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du topic, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                topic.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec du topic en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

}

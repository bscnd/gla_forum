package dao;

import beans.Message;
import beans.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initRequest;

public class DAOMessageImpl implements DAOMessage {

    private DAOFactory daoFactory;
    private static final String SQL_INSERT_MESSAGE = "INSERT INTO messages (auteur, contenu, thread) VALUES (?, ?, ?)";
    private static final String SQL_GET_THREAD_MESSAGES = "SELECT * FROM messages WHERE thread=?";

    public DAOMessageImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Message message) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequest( connexion, SQL_INSERT_MESSAGE, true, message.getAuteur(), message.getContenu(), message.getTopic());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du message, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                message.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec du message en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

    }

    public Set<Message> getThreadMessages (Long threadId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Message> threadMessages = new HashSet<>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequest(connexion, SQL_GET_THREAD_MESSAGES, false, threadId);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Message message = map(resultSet);
                threadMessages.add(message);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return threadMessages;
    }

    private static Message map( ResultSet resultSet ) throws SQLException {
        Message message = new Message();
        message.setContenu(resultSet.getString("contenu"));
        message.setAuteur(resultSet.getString("auteur"));
        message.setTopic(resultSet.getLong("thread"));
        message.setId(resultSet.getLong("id"));
        return message;
    }
}

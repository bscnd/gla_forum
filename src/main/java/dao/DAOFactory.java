package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "user";
    private static final String PROPERTY_MOT_DE_PASSE    = "password";

    private String              url;
    private String              username;
    private String              password;

    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC
     * @return une instance de Factory
     * @throws DAOConfigurationException si il y a un problème avec le DAO
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            username = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            password = properties.getProperty( PROPERTY_MOT_DE_PASSE );

        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        return new DAOFactory( url, username, password );
    }

    /**
     * Fourni une connexion à la base de données
     * @return un objet Connexion
     * @throws SQLException si une requête est invalide
     */
    /* package */ Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }


    /**
     * @return l'implémentation d'un DAOUser
     */
    public DAOUser getUtilisateurDao() {
        return new DAOUserImpl( this );
    }

    /**
     * @return l'implémentation d'un DAOTopic
     */
    public DAOTopic getTopicDao() {
        return new DAOTopicImpl( this );
    }

}
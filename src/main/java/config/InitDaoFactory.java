package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.DAOFactory;

public class InitDaoFactory implements ServletContextListener {
    private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory daoFactory;

    /**
     * Actions à réalisation lors du démarrage de l'application
     * @param event ouverture de l'application
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        /* Récupération du ServletContext lors du chargement de l'application */
        ServletContext servletContext = event.getServletContext();
        /* Instanciation de notre DAOFactory */
        this.daoFactory = DAOFactory.getInstance();
        /* Enregistrement dans un attribut ayant pour portée toute l'application */
        servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
    }

    /**
     * Actions à réaliser lors de la fermeture de l'application
     * @param event fermeture de l'application
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}

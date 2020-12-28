package dao;

import beans.UserProfile;

public interface DAOUser {

    void creer( UserProfile utilisateur ) throws DAOException;

    UserProfile trouver( String username ) throws DAOException;

}

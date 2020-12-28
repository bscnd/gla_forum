package dao;

import beans.UserProfile;

public class DAOUserImpl implements DAOUser{

    private DAOFactory daoFactory;

    DAOUserImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(UserProfile utilisateur) throws DAOException {

    }

    @Override
    public UserProfile trouver(String username) throws DAOException {
        return null;
    }
}

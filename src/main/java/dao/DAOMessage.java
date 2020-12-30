package dao;

import beans.Message;

public interface DAOMessage {
    void creer(Message message) throws DAOException;
}

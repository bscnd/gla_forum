package dao;

import beans.Message;

import java.util.Set;

public interface DAOMessage {
    void creer(Message message) throws DAOException;
    Set<Message> getThreadMessages (Long threadId) throws DAOException;
    }

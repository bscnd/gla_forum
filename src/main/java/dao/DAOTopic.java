package dao;

import beans.Topic;

import java.util.Set;

public interface DAOTopic {
    void creer( Topic topic ) throws DAOException;
    Set<Topic> getAllTopics() throws DAOException;
    Topic getTopicById(Long id) throws DAOException;
}

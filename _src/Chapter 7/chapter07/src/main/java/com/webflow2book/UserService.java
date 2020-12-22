package com.webflow2book;

import javax.persistence.EntityManager;

/**
 * Service for operations on User-objects.
 * 
 * @author Sven
 */
public interface UserService {

    /**
     * Returns the user with the given username.
     * @param username The username of the user to return
     * @return The user with the given username
     */
    User getUserByUsername(String username);

    /**
     * Changes the password of the user with the given
     * username to the given new password.
     * @param username The username of the user
     * @param password The new password for the user
     */
    void changePassword(String username, String password);

    /**
     * Setter for the EntityManager
     * @param entityManager The new EntityManager
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * Getter for the EntityManager
     * @return
     */
    EntityManager getEntityManager();
}

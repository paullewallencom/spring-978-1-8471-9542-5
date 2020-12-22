package com.webflow2book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Concrete implementation of the UserService-interface
 * @author Sven
 */
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        Query query = this.getEntityManager().createQuery(
                "select user from com.webflow2book.User user " + "where user.username like :username");
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

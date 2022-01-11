package com.moma.fans.data.dao;

import com.moma.fans.data.domain.User;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User> {

    private UserDAO() {}

    // Singleton on demand
    private static class InstanceHolder {
        private static final UserDAO INSTANCE = new UserDAO();
    }

    public static UserDAO getInstance() {
        return UserDAO.InstanceHolder.INSTANCE;
    }

    @Override
    public void save(User object) {

        super.saveObject(object);
    }

    @Override
    public void delete(User object) {

        super.deleteObject(object);
    }

    public User getByEmail(String email) {

        PersistenceManager pm = pmf.getPersistenceManager();

        Transaction tx = pm.currentTransaction();
        User user = null;

        try {

            tx.begin();

            Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + email + "'");
            query.setUnique(true);
            user = (User) query.execute();

            tx.commit();

        }

        catch (Exception e) {

            System.err.println("! Error obteniendo usuario por email");
            e.printStackTrace();
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }

            pm.close();
        }

        return user;

    }
}

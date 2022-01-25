package com.moma.fans.data.dao;

import com.moma.fans.data.domain.User;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

/**
 * Patrón DAO para objetos de tipo usuario.
 * @author JonanC
 */
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

    /**
     * Obtiene aquel usuario (local o no) que tenga registrado
     * un determinado correo electrónico.
     * @param email correo electrónico
     * @return usuario con el correo proporcionado, null si no existe
     */
    public User getByEmail(String email) {

        PersistenceManager pm = pmf.getPersistenceManager();

        Transaction tx = pm.currentTransaction();
        User user = null;

        try {

            tx.begin();

            Extent<?> e = pm.getExtent(User.class, true);
            Query<?> query = pm.newQuery(e);
            query.setUnique(true);
            query.setFilter("email == '" + email + "'");

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

package com.moma.fans.data.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 * Clase que implementa los métodos de interacción con BD más
 * comunes, de tal forma que las clases hijas no tengan que hacerlo y
 * baste con realizar una simple llamada.
 * @author JonanC
 */
public class DataAccessObjectBase {

    protected static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    /**
     * Guarda un objeto en la BD.
     * @param object objeto génerico
     */
    public void saveObject(Object object) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(object);
            tx.commit();
        }

        catch (Exception e) {

            System.out.println(" ! Error guardando objeto: " + e.getMessage());
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }
        }

    }

    /**
     * Elimina un objeto de la BD.
     * @param object objeto genérico
     */
    public void deleteObject(Object object) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.deletePersistent(object);
            tx.commit();
        }

        catch (Exception e) {

            System.out.println(" ! Error borrando objeto: " + e.getMessage());
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }
        }

    }
}

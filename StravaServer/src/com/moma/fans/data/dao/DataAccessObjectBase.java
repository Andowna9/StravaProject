package com.moma.fans.data.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

public class DataAccessObjectBase {

    protected static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

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

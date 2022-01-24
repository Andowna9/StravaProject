package com.moma.fans.data.dao;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {

    private ChallengeDAO() {}

    // Singleton on demand
    private static class InstanceHolder {
        private static final ChallengeDAO INSTANCE = new ChallengeDAO();
    }

    public static ChallengeDAO getInstance() {
        return ChallengeDAO.InstanceHolder.INSTANCE;
    }

    @Override
    public void save(Challenge object) {

        super.saveObject(object);
    }

    @Override
    public void delete(Challenge object) {

        super.deleteObject(object);
    }

    public Challenge getById(int challengeID) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        Challenge challenge = null;

        try {

            tx.begin();

            Extent<?> e = pm.getExtent(Challenge.class);
            Query<?> query = pm.newQuery(e);
            query.setFilter("id == " + challengeID);
            query.setUnique(true);

            challenge = (Challenge) query.execute();

            tx.commit();

        }

        catch (Exception e) {

            System.out.println("! Error obteniendo reto por id");
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }

            pm.close();
        }

        return challenge;
    }

    public List<Challenge> getAllChallenges() {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        List<Challenge> allChallenges = new ArrayList<>();

        try {

            tx.begin();

            Extent<?> e = pm.getExtent(Challenge.class, true);
            Query<?> query = pm.newQuery(e);
            allChallenges.addAll((List<Challenge>) query.execute());

            tx.commit();

        }

        catch (Exception e) {

            System.out.println("! Error obteniendo usuario por email");
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }

            pm.close();
        }

        return allChallenges;

    }
}

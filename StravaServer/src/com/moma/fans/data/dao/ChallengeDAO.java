package com.moma.fans.data.dao;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.challenge.ChallengeAssembler;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {

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

            Query<?> query = pm.newQuery("SELECT FROM " + Challenge.class.getName() + " WHERE id = " + challengeID);
            challenge = (Challenge) query.executeUnique();

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

        return challenge;
    }

    public List<Challenge> getAvailableChallenges(User user) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        List<Challenge> availableChallenges = new ArrayList<>();

        try {

            tx.begin();

            Query<?> query = pm.newQuery("SELECT * FROM " + Challenge.class.getName() + " WHERE CURDATE() BETWEEN startDate AND endDate");
            List<Challenge> results = query.executeResultList(Challenge.class);
            availableChallenges.addAll(results);

            tx.commit();

        }

        catch (Exception e) {

            System.out.println("! Error obteniendo lista de retos disponibles");
        }

        finally {

            if (tx != null && tx.isActive()) {

                tx.rollback();
            }

            pm.close();
        }

        return availableChallenges;
    }
}

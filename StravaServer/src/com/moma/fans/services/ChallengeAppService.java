package com.moma.fans.services;

import com.moma.fans.data.dao.ChallengeDAO;
import com.moma.fans.data.dao.UserDAO;
import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.ChallengeProgression;
import com.moma.fans.data.domain.TrainingSession;
import com.moma.fans.data.domain.User;

import java.util.*;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los retos.
 * @author JonanC
 * @author AlexNitu
 */
public class ChallengeAppService {

    // Eager initialization
    private static final ChallengeAppService INSTANCE = new ChallengeAppService();

    private ChallengeAppService() { }

    public static ChallengeAppService getInstance() {

        return INSTANCE;

    }

    /**
     * Obtiene la lista de retos disponibles
     * para un usuario, es decir, aquellos
     * que no son suyos y todavía no ha aceptado.
     * @param user usuario
     * @return
     */
    public List<Challenge> getAvailableChallenges(User user) {

        return ChallengeDAO.getInstance().getAvailableChallenges(user);
    }

    public List<Challenge> getCreatedChallenges(User user) {

        return user.getCreatedChallenges();
    }

    public List<ChallengeProgression> getAcceptedChallenges(User user) {

        List<ChallengeProgression> challengeProgressions = new ArrayList<>();

        for (Challenge challenge: user.getAcceptedChallenges()) {

            if (!challenge.hasEnded()) {

                double progress = 0.0d;

                for (TrainingSession trainingSession: user.getTrainingSessions()) {

                    progress += challenge.updateProgress(trainingSession);
                }

                ChallengeProgression challengeProgression = new ChallengeProgression();
                challengeProgression.setChallenge(challenge);
                challengeProgression.setProgress(Math.min(1.0d, progress));

                challengeProgressions.add(challengeProgression);

            }
        }

        return challengeProgressions;
    }


    public void createChallenge(User creator, Challenge challenge) {

        // Se genera un id y se añade el reto al usuario correspondiente

        challenge.setCreator(creator);
        creator.addCreatedChallenge(challenge);

        UserDAO.getInstance().save(creator);

    }

    public void acceptChallenge(User user, int challengeID) {

        // Obtener reto a partir de id y añadirlo a lista de usuario

        Challenge challenge = ChallengeDAO.getInstance().getById(challengeID);

        challenge.addParticipant(user);
        user.addAcceptedChallenge(challenge);

        UserDAO.getInstance().save(user);

    }


}

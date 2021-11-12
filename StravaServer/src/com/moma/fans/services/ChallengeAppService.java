package com.moma.fans.services;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.Sport;
import com.moma.fans.data.domain.User;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los retos.
 * @author JonanC
 * @author AlexNitu
 */
public class ChallengeAppService {

    private static int count = 0;

    Map<Integer, Challenge> allChallenges = new HashMap<>();

    public ArrayList<Challenge> getAvailable(){

        LocalDate today = LocalDate.now();
        ArrayList<Challenge> availableChallenges = new ArrayList<Challenge>();

        for (Challenge ch: allChallenges.values() ){
            if (ch.getStartDate().isAfter(today)){
                availableChallenges.add(ch);
            }
        }

        return availableChallenges;
    }


    public void createChallenge(User creator, Challenge challenge) {

        // Se genera un id y se añade el reto al usuario correspondiente
        challenge.setId(count++);
        challenge.setCreator(creator);
        creator.addCreatedChallenge(challenge);

        // Se añade a la estructura de datos que guarda todos los retos
        allChallenges.put(challenge.getId(), challenge);

    }

    public void acceptChallenge(User user, int challengeID) {

        // Obtener reto a partir de id y añadirlo a lista de usuario

        Challenge challenge = allChallenges.get(challengeID);
        user.addAcceptedChallenge(challenge);

    }

    public void setChallengeToActive(Challenge nChallenge){
        activeChallenges.add(nChallenge);
    }

    public void setChallengeToFinished(Challenge nChallenge){
        finishedChallenges.add(nChallenge);
    }

    public ArrayList<Challenge> getActiveChallenges(){

    }

    public ArrayList<Challenge> getFinishedChallenges(){
        return finishedChallenges;
    }




}

package com.moma.fans.services;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.Sport;
import com.moma.fans.data.domain.User;

import java.time.Duration;
import java.util.*;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los retos.
 * @author JonanC
 * @author AlexNitu
 */
public class ChallengeAppService {

    List<Challenge> allChallenges = new ArrayList<Challenge>();
    Map<User, ArrayList<Challenge>> userChallenges = new HashMap<User, ArrayList<Challenge>>();

    ArrayList<Challenge> activeChallenges = new ArrayList<Challenge>();
    ArrayList<Challenge> finishedChallenges = new ArrayList<Challenge>();


    public boolean createChallenge(int id, String name, Date startDate, Date endDate, Double distance, Duration timeToAchieve, Sport sport){
        boolean success = false;

        Challenge newChallenge = new Challenge();
        newChallenge.setId(id);
        newChallenge.setName(name);
        newChallenge.setStartDate(startDate);
        newChallenge.setEndDate(endDate);
        newChallenge.setDistance(distance);
        newChallenge.setTimeToAchieve(timeToAchieve);
        newChallenge.setSport(sport);

        try {
            allChallenges.add(newChallenge);
            success = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

    public void setChallengeToActive(Challenge nChallenge){
        activeChallenges.add(nChallenge);
    }

    public void setChallengeToFinished(Challenge nChallenge){
        finishedChallenges.add(nChallenge);
    }

    public ArrayList<Challenge> getActiveChallenges(){
        return activeChallenges;
    }

    public ArrayList<Challenge> getFinishedChallenges(){
        return finishedChallenges;
    }




}

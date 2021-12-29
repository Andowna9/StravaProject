package com.moma.fans.controllers;

import com.moma.fans.data.dto.challenge.AcceptedChallengeDTO;
import com.moma.fans.data.dto.challenge.ChallengeCreationDTO;
import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.remote.ServiceLocator;

import java.rmi.RemoteException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de operaciones con retos
 * (creación, recuperación, apuntarse, etc.).
 * @author JonanC
 */
public class ChallengeController {

    private ServiceLocator serviceLocator;

    public ChallengeController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }

    public void createDistanceChallenge(long token, ChallengeCreationDTO challengeCreationDTO, double distanceToAchieve) throws RemoteException {


        serviceLocator.getService().createDistanceChallenge(token, challengeCreationDTO, distanceToAchieve);
    }

    public void createTimeChallenge(long token, ChallengeCreationDTO challengeCreationDTO, Duration timeToAchieve) throws RemoteException {


        serviceLocator.getService().createTimeChallenge(token, challengeCreationDTO, timeToAchieve);
    }


    public List<ChallengeDTO> getCreatedChallenges(long token) {

        try {
            return this.serviceLocator.getService().getCreatedChallenges(token);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<ChallengeDTO> getAvailableChallenges(long token) {

        try {
            return this.serviceLocator.getService().getAvailableChallenges(token);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void acceptChallenge(long token, int challengeID) throws RemoteException {

        this.serviceLocator.getService().acceptChallenge(token, challengeID);
    }

    public List<AcceptedChallengeDTO> getAcceptedChallenges(long token) {

        try {

            return this.serviceLocator.getService().getAcceptedChallenges(token);
        }

        catch (RemoteException e) {

            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}

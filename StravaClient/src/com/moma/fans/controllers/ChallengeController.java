package com.moma.fans.controllers;

import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.remote.ServiceLocator;

import java.rmi.RemoteException;
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

    public void createChallenge(long token, ChallengeDTO challengeDTO) throws RemoteException {


        serviceLocator.getService().createChallenge(token, challengeDTO);
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
}

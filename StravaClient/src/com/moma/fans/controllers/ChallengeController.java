package com.moma.fans.controllers;

import com.moma.fans.data.dto.ChallengeCreationDTO;
import com.moma.fans.remote.ServiceLocator;

import java.rmi.RemoteException;

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

    public void createChallenge(long token, ChallengeCreationDTO challengeDTO) throws RemoteException {


            serviceLocator.getService().createChallenge(token, challengeDTO);

    }
}

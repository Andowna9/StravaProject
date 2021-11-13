package com.moma.fans.controllers;

import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.remote.ServiceLocator;

import java.rmi.RemoteException;

/**
 * Controlador de operaciones con sesiones
 * (creación, recuperación, etc.).
 * @author JonanC
 */
public class TrainingSessionController {

    private ServiceLocator serviceLocator;

    public TrainingSessionController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }

    public void createTrainingSession(long token, TrainingSessionDTO trainingSessionDTO) throws RemoteException {

        serviceLocator.getService().createTrainingSession(token, trainingSessionDTO);
    }
}

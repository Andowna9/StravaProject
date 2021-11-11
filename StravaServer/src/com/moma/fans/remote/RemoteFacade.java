package com.moma.fans.remote;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.*;
import com.moma.fans.services.ChallengeAppService;
import com.moma.fans.services.TrainingSessionAppService;
import com.moma.fans.services.UserAppService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.*;

/**
 * Esta clase es una Fachada Remota que implementa la funcionalidad del servidor
 * mediante llamadas a distintos servicios.
 * @author JonanC
 */
public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {

    // Estado del servidor
    Map<Long, User> serverState = new HashMap<>();

    // Servicios
    UserAppService userService = new UserAppService();
    TrainingSessionAppService trainingSessionService = new TrainingSessionAppService();
    ChallengeAppService challengeService = new ChallengeAppService();

    // DTO Assemblers
    UserAssembler userAssembler = new UserAssembler();
    TrainingSessionAssembler trainingSessionAssembler = new TrainingSessionAssembler();
    ChallengeAssembler challengeAssembler = new ChallengeAssembler();

    public RemoteFacade() throws RemoteException {
        super();
    }

    @Override
    public long register(UserCreationDTO userDTO) throws RemoteException {

        User user = userAssembler.toUser(userDTO);
        boolean isValid = userService.register(user);

        if (isValid) {

            return Calendar.getInstance().getTimeInMillis();

        }

        else {

            throw new RemoteException("La cuenta ya existe!");
        }

    }

    @Override
    public synchronized long login(String email, String password) throws RemoteException {

        User user = userService.login(email, password);

        if (user != null) {

            if (!serverState.containsValue(user)) {

                long token = Calendar.getInstance().getTimeInMillis();
                serverState.put(token, user);

                return token;

            } else {

                throw new RemoteException("Ya se ha iniciado sesión!");
            }

        }

        else {

            throw new RemoteException("Credenciales incorrectas!");
        }

    }

    @Override
    public synchronized void logout(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            serverState.remove(token);
        }

        else {

            throw new RemoteException("La sesión proporcionada no existe!");

        }


    }

    @Override
    public boolean createTrainingSession(long token, TrainingSessionDTO trainingSessionDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token); // TODO Mejorar con servicio
            user.addTrainingSession(trainingSessionAssembler.toTrainingSession(trainingSessionDTO));

            return true;

        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar las sesiones de entrenamiento");
        }
    }

    @Override
    public boolean createChallenge(long token, ChallengeCreationDTO challengeDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            Challenge challenge = challengeAssembler.toChallenge(challengeDTO);

            challengeService.createChallenge(user, challenge);

            return true;
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para crear un reto");
        }
    }

    @Override
    public boolean acceptChallenge(long token, int challengeID) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            challengeService.acceptChallenge(user, challengeID);

            return true;
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para poder aceptar un reto");
        }
    }

    @Override
    public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token); // TODO Mejorar con servicio
            return trainingSessionAssembler.toDTO(user.getTrainingSessions());
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar las sesiones de entrenamiento");
        }

    }

    @Override
    public HashMap<String, List<ChallengeDTO>> getChallenges(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            return null;
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar los retos");
        }
    }
}

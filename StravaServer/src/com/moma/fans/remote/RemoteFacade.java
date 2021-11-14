package com.moma.fans.remote;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.challenge.ChallengeAssembler;
import com.moma.fans.data.dto.challenge.ChallengeCreationDTO;
import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.data.dto.session.TrainingSessionAssembler;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.data.dto.user.UserAssembler;
import com.moma.fans.data.dto.user.UserDTO;
import com.moma.fans.services.ChallengeAppService;
import com.moma.fans.services.TrainingSessionAppService;
import com.moma.fans.services.UserAppService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Logger;

/**
 * Esta clase es una Fachada Remota que implementa la funcionalidad del servidor
 * mediante llamadas a distintos servicios.
 * @author JonanC
 */
public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {

    private Logger logger = Logger.getLogger(RemoteFacade.class.getName());

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
    public synchronized long register(String email, String nickname, String password) throws RemoteException {


        User user = userService.registerUser(email, nickname, password);

        if (user != null) {

            long token = Calendar.getInstance().getTimeInMillis();
            serverState.put(token, user);

            logger.info("[Nuevo usuario registrado]" +
                    "\n" + user +
                    "\n" + "Token: " + token);

            return token;

        }

        else {

            throw new RemoteException("La cuenta ya existe!");
        }

    }

    @Override
    public void createProfile(long token, ProfileCreationDTO userDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            userAssembler.createProfile(user, userDTO);
        }
        else {

            throw new RemoteException("El perfil creado no corresponde a ningún usuario");
        }
    }

    @Override
    public synchronized long login(String email, String password) throws RemoteException {

        User user = userService.login(email, password);

        if (user != null) {

            if (!serverState.containsValue(user)) {

                long token = Calendar.getInstance().getTimeInMillis();
                serverState.put(token, user);

                logger.info("[Inicio de sesión]" +
                        "\n" + user +
                        "\n" + "Token: " + token);

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

            User user = serverState.remove(token);

            logger.info("[Fin de sesión]" +
                    "\n" + user);

        }

        else {

            throw new RemoteException("La sesión proporcionada no existe!");

        }


    }

    @Override
    public UserDTO getUserData(long token) {

       User user = serverState.get(token);
       return userAssembler.toDTO(user);
    }

    @Override
    public boolean createTrainingSession(long token, TrainingSessionDTO trainingSessionDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
           trainingSessionService.createTrainingSession(user, trainingSessionAssembler.toTrainingSession(trainingSessionDTO));

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

            User user = serverState.get(token);
            return trainingSessionAssembler.toDTO(trainingSessionService.getTrainingSessions(user));
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar las sesiones de entrenamiento");
        }

    }

    @Override
    public List<ChallengeDTO> getCreatedChallenges(long token) throws RemoteException {

        User user = serverState.get(token);
        return challengeAssembler.toDTO(challengeService.getCreatedChallenges(user));
    }

    @Override
    public List<ChallengeDTO> getAvailableChallenges(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            return challengeAssembler.toDTO(challengeService.getAvailableChallenges(user));
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar los retos");
        }
    }
}

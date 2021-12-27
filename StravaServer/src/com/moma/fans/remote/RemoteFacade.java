package com.moma.fans.remote;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.RegisterType;
import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.challenge.AcceptedChallengeDTO;
import com.moma.fans.data.dto.challenge.ChallengeAssembler;
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

    public RemoteFacade() throws RemoteException {
        super();
    }

    @Override
    public synchronized long register(String email, String nickname, String password, String registerType) throws RemoteException {

        RegisterType rt = RegisterType.valueOfRegisterType(registerType);
        User user = UserAppService.getInstance().registerUser(email, nickname, password, rt);

        if (user != null) {

            long token = Calendar.getInstance().getTimeInMillis();
            serverState.put(token, user);

            logger.info("[Nuevo usuario registrado]" +
                    "\n" + user +
                    "\n" + "Tipo de registro: " + registerType +
                    "\n" + "Token: " + token);

            return token;

        }

        else {

            throw new RemoteException("Registro inválido!");
        }

    }

    @Override
    public void createProfile(long token, ProfileCreationDTO userDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            UserAssembler.getInstance().createProfile(user, userDTO);
        }
        else {

            throw new RemoteException("El perfil creado no corresponde a ningún usuario");
        }
    }

    @Override
    public synchronized long login(String email, String password) throws RemoteException {

        User user = UserAppService.getInstance().login(email, password);

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
       return UserAssembler.getInstance().toUserDTO(user);
    }

    @Override
    public boolean createTrainingSession(long token, TrainingSessionDTO trainingSessionDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            TrainingSessionAppService.getInstance().createTrainingSession(user,
                   TrainingSessionAssembler.getInstance().toTrainingSession(trainingSessionDTO));

            return true;

        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar las sesiones de entrenamiento");
        }
    }

    @Override
    public boolean createChallenge(long token, ChallengeDTO challengeDTO) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            Challenge challenge = ChallengeAssembler.getInstance().toChallenge(challengeDTO);

            ChallengeAppService.getInstance().createChallenge(user, challenge);

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
            ChallengeAppService.getInstance().acceptChallenge(user, challengeID);

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
            return TrainingSessionAssembler.getInstance().toTrainingSessionDTO(
                    TrainingSessionAppService.getInstance().getTrainingSessions(user));
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar las sesiones de entrenamiento");
        }

    }

    @Override
    public List<ChallengeDTO> getCreatedChallenges(long token) throws RemoteException {

        User user = serverState.get(token);
        return ChallengeAssembler.getInstance().toChallengeDTO(
                ChallengeAppService.getInstance().getCreatedChallenges(user));
    }

    @Override
    public List<ChallengeDTO> getAvailableChallenges(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            return ChallengeAssembler.getInstance().toChallengeDTO(
                    ChallengeAppService.getInstance().getAvailableChallenges(user));
        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar los retos");
        }
    }

    @Override
    public List<AcceptedChallengeDTO> getAcceptedChallenges(long token) throws RemoteException {

        if (serverState.containsKey(token)) {

            User user = serverState.get(token);
            return ChallengeAssembler.getInstance().toAcceptedChallengeDTO(
                    ChallengeAppService.getInstance().getAcceptedChallenges(user));

        }

        else {

            throw new RemoteException("Hay que iniciar sesión para consultar los retos aceptados");
        }
    }
}

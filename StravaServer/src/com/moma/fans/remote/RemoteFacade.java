package com.moma.fans.remote;

import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.ChallengeDTO;
import com.moma.fans.data.dto.TrainingSessionDTO;
import com.moma.fans.data.dto.UserAssembler;
import com.moma.fans.data.dto.UserCreationDTO;
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
    public boolean createTrainingSession(long token, String title, String sport, double distance, Date date, Duration duration) throws RemoteException {
        return false;
    }

    @Override
    public boolean createChallenge(long token, String title, String sport, Date startDate, Date endDate, double distanceToAchieve, Duration timeToAchieve) throws RemoteException {
        return false;
    }

    @Override
    public boolean acceptChallenge(long token, int challengeID) throws RemoteException {
        return false;
    }

    @Override
    public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException {
        return null;
    }

    @Override
    public HashMap<String, List<ChallengeDTO>> getChallenges(long token) throws RemoteException {
        return null;
    }
}

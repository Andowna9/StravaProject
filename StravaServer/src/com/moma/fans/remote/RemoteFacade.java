package com.moma.fans.remote;

import com.moma.fans.data.dto.ChallengeDTO;
import com.moma.fans.data.dto.TrainingSessionDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase es una Fachada Remota que implementa la interfaz del servidor
 * mediante llamadas a distintos servicios.
 * @author JonanC
 */
public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {

    public RemoteFacade() throws RemoteException {
        super();
    }

    @Override
    public void register(String email, String passHash, String nickname, Date birthDate, float weight, float height, short minHeartRate, short maxHeartRate) throws RemoteException {

    }

    @Override
    public long login(String email, String passHash) throws RemoteException {
        return 0;
    }

    @Override
    public void logout(long token) throws RemoteException {

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

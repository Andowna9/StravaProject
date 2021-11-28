package com.moma.fans.remote;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.data.dto.user.UserAssembler;
import com.moma.fans.data.dto.user.UserDTO;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoteFacadeTest {

    IRemoteFacade remoteFacade = new RemoteFacade();
    User user = new User();
    UserAssembler uA = new UserAssembler();
    long token = remoteFacade.register("alex@gmail.com", "alex", "123456", "");


    RemoteFacadeTest() throws RemoteException {
    }

    @Test
    void register() {
        assertEquals(token, Calendar.getInstance().getTimeInMillis());
    }

    @Test
    void createProfile() {  // Puede que no se necesite porque es un void
        long tokenI = Calendar.getInstance().getTimeInMillis();

        assertEquals(tokenI, remoteFacade.createProfile(tokenI, uA.toDTO(user)));
    }

    @Test
    void login() {
        long tokenI = Calendar.getInstance().getTimeInMillis();

        assertEquals(tokenI, remoteFacade.login("alex@gmail.com", "123456"));
    }

    @Test
    void logout() { // void

    }

    @Test
    void getUserData() {
        long tokenI = Calendar.getInstance().getTimeInMillis();
        assertEquals(uA.toDTO(user),tokenI);
    }

    @Test
    void createTrainingSession() {
        TrainingSessionDTO tDTO = new TrainingSessionDTO();
        assertEquals(true, remoteFacade.createTrainingSession(token, tDTO));
    }

    @Test
    void createChallenge() {
        ChallengeDTO cDTO = new ChallengeDTO();
        assertEquals(true, remoteFacade.createChallenge(token, cDTO));
    }

    @Test
    void acceptChallenge() {
        Challenge challenge = new Challenge();
        assertEquals(true, remoteFacade.acceptChallenge(token, challenge));
    }

    @Test
    void getTrainingSessions() {
        List<TrainingSessionDTO> trainingSList = new ArrayList<TrainingSessionDTO>();
        assertArrayEquals(trainingSList, remoteFacade.getTrainingSessions());
    }

    @Test
    void getCreatedChallenges() {
        List<ChallengeDTO> challengeDTOS = new ArrayList<ChallengeDTO>();
        assertArrayEquals(challengeDTOS, remoteFacade.getCreatedChallenges());
    }

    @Test
    void getAvailableChallenges() {
        List<ChallengeDTO> challengeDTOS = new ArrayList<ChallengeDTO>();
        assertArrayEquals(challengeDTOS, remoteFacade.getCreatedChallenges());
    }
}
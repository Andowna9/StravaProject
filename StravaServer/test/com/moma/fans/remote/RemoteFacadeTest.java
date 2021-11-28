package com.moma.fans.remote;

import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.data.dto.user.UserDTO;
import org.junit.jupiter.api.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test multiusuario de la fachada remota.
 * Se ha utilizado Junit5.
 * @author AlexNitu
 * @author JonanC
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RemoteFacadeTest {

    // Fachada remota
    static IRemoteFacade remoteFacade;

    // Tokens de usuarios
    static long user1Token;
    static long user2Token;
    static long user3Token;

    @BeforeAll
    static void setup() {

        try {

            remoteFacade = new RemoteFacade();

            // Registro y log-out (para poder realizar la prueba de login posteriormente)
            long token1 = remoteFacade.register("ibai@gmail.com", "Ibai", "txda24", "Local");
            remoteFacade.logout(token1);

            long token2 = remoteFacade.register("elrubius@gmail.com", "Rubiuh", "732sd9", "Local");
            remoteFacade.logout(token2);

            long token3 = remoteFacade.register("willyrex@gmail.com", "WillyRex", "gashd042g", "Local");
            remoteFacade.logout(token3);
        }

        catch (RemoteException e) {

            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(1)
    void testLogin() {

        // Login válido de usuarios

        try {

            user1Token = remoteFacade.login("ibai@gmail.com", "txda24");
            user2Token = remoteFacade.login("elrubius@gmail.com", "732sd9");
            user3Token = remoteFacade.login("willyrex@gmail.com", "gashd042g");

        } catch (RemoteException e) {
            e.printStackTrace();
            fail();
        }

        // Login inválido - El usuario no existe
        assertThrows(RemoteException.class, () -> {
            remoteFacade.login("wrong@gmail.com", "PASS");
        });

        // Login inválido - Contraseña incorrecta
        assertThrows(RemoteException.class, () -> {
            remoteFacade.login("ibai@gmail.com", "jka");
        });

    }


    @Test
    @Order(2)
    void testCreateProfile() {

        try {

            ProfileCreationDTO profileDTO = new ProfileCreationDTO();
            profileDTO.setBirthDate(LocalDate.ofYearDay(1997, 1));
            profileDTO.setWeight(75.0f);
            profileDTO.setHeight(180.0f);
            profileDTO.setMaxHeartRate((short) 180);
            profileDTO.setMinHeartRate((short) 120);

            remoteFacade.createProfile(user1Token, profileDTO);
            UserDTO userDTO = remoteFacade.getUserData(user1Token);

            assertEquals(profileDTO.getBirthDate(), userDTO.getBirthDate());
            assertEquals(profileDTO.getWeight(), userDTO.getWeight());
            assertEquals(profileDTO.getHeight(), userDTO.getHeight());
            assertEquals(profileDTO.getMaxHeartRate(), userDTO.getMaxHeartRate());
            assertEquals(profileDTO.getMinHeartRate(), userDTO.getMinHeartRate());

        } catch (RemoteException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    @Order(3)
    void testTrainingSessions() {

        try {

            // 1 Sesión
            remoteFacade.createTrainingSession(user1Token, new TrainingSessionDTO());

            // 2 Sesiones
            remoteFacade.createTrainingSession(user2Token, new TrainingSessionDTO());
            remoteFacade.createTrainingSession(user2Token, new TrainingSessionDTO());

            // 3 Sesiones
            remoteFacade.createTrainingSession(user3Token, new TrainingSessionDTO());
            remoteFacade.createTrainingSession(user3Token, new TrainingSessionDTO());
            remoteFacade.createTrainingSession(user3Token, new TrainingSessionDTO());

            List<TrainingSessionDTO> sessions1 = remoteFacade.getTrainingSessions(user1Token);
            assertEquals(1, sessions1.size());

            List<TrainingSessionDTO> sessions2 = remoteFacade.getTrainingSessions(user2Token);
            assertEquals(2, sessions2.size());

            List<TrainingSessionDTO> sessions3 = remoteFacade.getTrainingSessions(user3Token);
            assertEquals(3, sessions3.size());

        } catch (RemoteException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    @Order(4)
    void testChallenges() {

        try {

            // Creación de retos

            ChallengeDTO ch1 = new ChallengeDTO();
            ch1.setStartDate(LocalDate.now().plusDays(1));
            ch1.setEndDate(LocalDate.now().plusDays(5));
            remoteFacade.createChallenge(user1Token, ch1);

            ChallengeDTO ch2 = new ChallengeDTO();
            ch2.setStartDate(LocalDate.now().plusDays(2));
            ch2.setEndDate(LocalDate.now().plusDays(10));
            remoteFacade.createChallenge(user2Token, ch2);

            ChallengeDTO ch3 = new ChallengeDTO();
            ch3.setStartDate(LocalDate.now().plusDays(1));
            ch3.setEndDate(LocalDate.now().plusDays(3));
            remoteFacade.createChallenge(user3Token, ch3);

            // Recuperción de retos

            // Retos creados

            List<ChallengeDTO> user1Challenges = remoteFacade.getCreatedChallenges(user1Token);
            List<ChallengeDTO> user2Challenges = remoteFacade.getCreatedChallenges(user2Token);
            List<ChallengeDTO> user3Challenges = remoteFacade.getCreatedChallenges(user3Token);

            assertEquals(1, user1Challenges.size());
            assertEquals(1, user2Challenges.size());
            assertEquals(1, user3Challenges.size());

            // Retos disponibles

           assertEquals(user2Challenges.size() + user3Challenges.size(),
                    remoteFacade.getAvailableChallenges(user1Token).size());

           assertEquals(user1Challenges.size() + user3Challenges.size(),
                    remoteFacade.getAvailableChallenges(user2Token).size());

           assertEquals(user1Challenges.size() + user2Challenges.size(),
                    remoteFacade.getAvailableChallenges(user3Token).size());

           // TODO Retos aceptados


        } catch (RemoteException e) {
            e.printStackTrace();
            fail();
        }

    }


    @AfterAll
    static void done() {

        try {

            remoteFacade.logout(user1Token);
            remoteFacade.logout(user2Token);
            remoteFacade.logout(user3Token);

        }

        catch (RemoteException e) {

            e.printStackTrace();
            fail();
        }
    }

}
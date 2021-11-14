package com.moma.fans.remote;

import com.moma.fans.data.dto.challenge.ChallengeCreationDTO;
import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.data.dto.user.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz que define la funcionalidad que el servidor expone.
 * @author JonanC
 */
public interface IRemoteFacade extends Remote {

    /**
     * Crea una cuenta de usuario.
     * @param email correo electrónico
     * @param nickname nombre de usuario
     * @param password contraseña
     */
    public long register(String email, String nickname, String password) throws RemoteException;

    /**
     * Recibe datos de una cuenta de usuario y los
     * guarda en el servidor.
     * @param userDTO contenedor con información del perfil del usuario
     */
    public void createProfile(long token, ProfileCreationDTO userDTO) throws RemoteException;

    /**
     * Crea una sesión antes de que el usuario pueda acceder
     * al resto de la funcionalidad.
     * @param email correo electrónico
     * @param password contraseña
     * @return token para identificar la sesión del usuario
     * @throws RemoteException si las credenciales no han sido registradas
     * (el usuario no existe)
     */
    public long login(String email, String password) throws RemoteException;

    /**
     * Finaliza la sesión con el servidor.
     * @param token token devuelto al iniciar sesión
     */
    public void logout(long token) throws RemoteException;

    /**
     * Devuelve toda la información del perfil de usuario.
     * @param token identificación del usuario
     * @return contenedor con información de usuario
     */
    public UserDTO getUserData(long token) throws RemoteException;

    /**
     * Registra la sesión de entrenamiento de un usuario dado.
     * @param token identificación del usuario
     * @param trainingSessionDTO conteneder de información de sesión de entrenamiento
     * @return true si se ha podido crear la sesión correctamente, false en caso contrario
     */
    public boolean createTrainingSession(long token, TrainingSessionDTO trainingSessionDTO) throws RemoteException;

    /**
     * Crea un reto al que cualquier usuario puede apuntarse.
     * @param token identificación del usuario
     * @param  challengeDTO contenedor de información de reto
     * @return true si se crea el reto correctamente, false en caso contrario
     */
    public boolean createChallenge(long token, ChallengeCreationDTO challengeDTO) throws RemoteException;

    /**
     * Añade un reto a la lista de aceptados de un usuario.
     * @param token identificación del usuario
     * @param challengeID identificación del reto
     * @return true si el reto es aceptadi, false si hay algún problema
     */
    public boolean acceptChallenge(long token, int challengeID) throws RemoteException;

    /**
     * @param token identificación del usuario
     * @return lista de sesiones de entrenamiento en formato DTO
     */
    public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException;

    /**
     *
     * @param token identificación del usuario
     * @return lista de retos creados
     */
    public List<ChallengeDTO> getCreatedChallenges(long token) throws RemoteException;

    /**
     * @param token identificación del usuario
     * @return retos divididos en categorías (propios, aceptados y disponibles)
     */
     public List<ChallengeDTO> getAvailableChallenges(long token) throws RemoteException;


}

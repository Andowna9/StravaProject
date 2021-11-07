package com.moma.fans.remote;

import com.moma.fans.data.dto.ChallengeDTO;
import com.moma.fans.data.dto.TrainingSessionDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Interfaz que define la funcionalidad que el servidor expone.
 * @author JonanC
 */
public interface IRemoteFacade extends Remote {

    /**
     * Crea una cuenta de usuario.
     * @param email correo electrónico
     * @param passHash contraseña con algoritmo hash aplicada
     * @param nickname nombre de usuario
     * @param birthDate fecha de nacimiento
     * @param weight peso
     * @param height altura
     * @param minHeartRate mínima frecuencia cardiaca
     * @param maxHeartRate máxima frecuencia cardiaca
     */
    public void register(String email, String passHash, String nickname, Date birthDate,
                         float weight, float height,
                         short minHeartRate, short maxHeartRate) throws RemoteException;

    /**
     * Crea una sesión antes de que el usuario pueda acceder
     * al resto de la funcionalidad.
     * @param email correo electrónico
     * @param passHash contraseña encriptada como hash
     * @return token para identificar la sesión del usuario
     * @throws RemoteException si las credenciales no han sido registradas
     * (el usuario no existe)
     */
    public long login(String email, String passHash) throws RemoteException;

    /**
     * Finaliza la sesión con el servidor.
     * @param token token devuelto al iniciar sesión
     */
    public void logout(long token) throws RemoteException;

    /**
     * Registra la sesión de entrenamiento de un usuario dado.
     * @param token identificación del usuario
     * @param title título de la sesión
     * @param sport deporte asociado
     * @param distance distancia recorrida (km)
     * @param date fecha
     * @param duration duración de la actividad (min)
     * @return true si se ha podido crear la sesión correctamente, false en caso contrario
     */
    public boolean createTrainingSession(long token, String title, String sport,
                                         double distance, Date date, Duration duration) throws RemoteException;

    /**
     * Crea un reto al que cualquier usuario puede apuntarse.
     * @param token identificación del usuario
     * @param title título del reto
     * @param sport deporte asociado
     * @param startDate fecha de inicio
     * @param endDate fecha de finalización
     * @param distanceToAchieve distancia objetivo (km)
     * @param timeToAchieve tiempo objetivo (min)
     * @return true si se crea el reto correctamente, false en caso contrario
     */
    public boolean createChallenge(long token, String title, String sport,
                                   Date startDate, Date endDate,
                                   double distanceToAchieve, Duration timeToAchieve) throws RemoteException;

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
     * @param token identificación del usuario
     * @return retos divididos en categorías (propios, aceptados y disponibles)
     */
     public HashMap<String, List<ChallengeDTO>> getChallenges(long token) throws RemoteException;


}

package com.moma.fans.services;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.User;

import java.time.LocalDate;
import java.util.*;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los retos.
 * @author JonanC
 * @author AlexNitu
 */
public class ChallengeAppService {

    private static int count = 0;

    Map<Integer, Challenge> allChallenges = new HashMap<>();

    /**
     * Obtiene la lista de retos disponibles
     * para un usuario, es decir, aquellos
     * que no son suyos y todavía no ha aceptado.
     * @param user usuario
     * @return
     */
    public List<Challenge> getAvailableChallenges(User user) {

        LocalDate today = LocalDate.now(); // Obtenemos la fecha de hoy
        List<Challenge> availableChallenges = new ArrayList<>();

        for (Challenge ch: allChallenges.values()) {

            // Si todavía no ha terminado el plazo
            if (today.isAfter(ch.getStartDate()) && today.isBefore(ch.getEndDate())){

                // Si el reto no ha sido aceptado ni creado por el usuario
                if (!user.getAcceptedChallenges().contains(ch) && !user.getCreatedChallenges().contains(ch)) {

                    availableChallenges.add(ch);
                }
            }
        }

        return availableChallenges;
    }


    public void createChallenge(User creator, Challenge challenge) {

        // Se genera un id y se añade el reto al usuario correspondiente
        challenge.setId(count++);
        challenge.setCreator(creator);
        creator.addCreatedChallenge(challenge);

        // Se añade a la estructura de datos que guarda todos los retos
        allChallenges.put(challenge.getId(), challenge);

    }

    public void acceptChallenge(User user, int challengeID) {

        // Obtener reto a partir de id y añadirlo a lista de usuario

        Challenge challenge = allChallenges.get(challengeID);
        user.addAcceptedChallenge(challenge);

    }


}

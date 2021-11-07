package com.moma.fans.data.dto;

import com.moma.fans.data.domain.Challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.Challenge}
 * a objetos {@link ChallengeDTO}.
 * @author JonanC
 */
public class ChallengeAssembler {

    public ChallengeDTO challengeToDTO(Challenge challenge) {

        ChallengeDTO challengeDTO = new ChallengeDTO();

        challengeDTO.setTitle(challenge.getTitle());
        challengeDTO.setSport(challenge.getSport().toString());
        challengeDTO.setStartDate(challenge.getStartDate());
        challengeDTO.setEndDate(challenge.getEndDate());
        challengeDTO.setTimeToAchieve(challenge.getTimeToAchieve());
        challengeDTO.setDistanceToAchieve(challenge.getDistanceToAchieve());
        // challengeDTO.setProgress(); TODO

        return challengeDTO;
    }

    public List<ChallengeDTO> challengesToDTO(List<Challenge> challenges) {

        List<ChallengeDTO> challengesDTO = new ArrayList<>();

        for (Challenge ch: challenges) {

            challengesDTO.add(this.challengeToDTO(ch));
        }

        return challengesDTO;
    }
}

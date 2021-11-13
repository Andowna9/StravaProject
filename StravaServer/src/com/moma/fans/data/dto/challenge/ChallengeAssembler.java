package com.moma.fans.data.dto.challenge;

import com.moma.fans.data.domain.Challenge;
import com.moma.fans.data.domain.Sport;

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

    public ChallengeDTO toDTO(Challenge challenge) {

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

    public List<ChallengeDTO> toDTO(List<Challenge> challenges) {

        List<ChallengeDTO> challengesDTO = new ArrayList<>();

        for (Challenge ch: challenges) {

            challengesDTO.add(this.toDTO(ch));
        }

        return challengesDTO;
    }

    public Challenge toChallenge(ChallengeCreationDTO challengeDTO) {

        return new Challenge(challengeDTO.getTitle(), challengeDTO.getStartDate(),
                challengeDTO.getEndDate(),challengeDTO.getDistanceToAchieve(), challengeDTO.getTimeToAchieve(),
                Sport.valueOf(challengeDTO.getSport()));
    }
}

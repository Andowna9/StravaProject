package com.moma.fans.data.dto.challenge;

import com.moma.fans.data.domain.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.Challenge}
 * a objetos {@link AcceptedChallengeDTO}.
 * Implementa el patrón de diseño Singleton.
 * @author JonanC
 */
public class ChallengeAssembler {

    private ChallengeAssembler() { }

    // Singleton on demand
    private static class InstanceHolder {
        private static final ChallengeAssembler INSTANCE = new ChallengeAssembler();
    }

    public static ChallengeAssembler getInstance() {
       return InstanceHolder.INSTANCE;
    }

    public ChallengeDTO toChallengeDTO(Challenge challenge) {

        ChallengeDTO challengeDTO = new AcceptedChallengeDTO();

        challengeDTO.setId(challengeDTO.getId());
        challengeDTO.setTitle(challenge.getTitle());
        challengeDTO.setSport(challenge.getSport().toString());
        challengeDTO.setStartDate(challenge.getStartDate());
        challengeDTO.setEndDate(challenge.getEndDate());
        challengeDTO.setGoal(challenge.getObjective());

        return challengeDTO;
    }

    public List<ChallengeDTO> toChallengeDTO(List<Challenge> challenges) {

        List<ChallengeDTO> challengesDTO = new ArrayList<>();

        for (Challenge ch: challenges) {

            challengesDTO.add(this.toChallengeDTO(ch));
        }

        return challengesDTO;
    }

    private void setUpChallenge(Challenge challenge, ChallengeCreationDTO challengeCreationDTO) {

        challenge.setTitle(challengeCreationDTO.getTitle());
        challenge.setSport(Sport.valueOfSport(challengeCreationDTO.getSport()));
        challenge.setStartDate(challengeCreationDTO.getStartDate());
        challenge.setEndDate(challengeCreationDTO.getEndDate());
    }

    public Challenge toDistanceChallenge(ChallengeCreationDTO challengeCreationDTO, double distanceToAchieve) {

        Challenge challenge = new DistanceChallenge(distanceToAchieve);
        setUpChallenge(challenge, challengeCreationDTO);

        return challenge;
    }

    public Challenge toTimeChallenge(ChallengeCreationDTO challengeCreationDTO, Duration timeToAchieve) {

        Challenge challenge = new TimeChallenge(timeToAchieve);
        setUpChallenge(challenge, challengeCreationDTO);

        return challenge;

    }

    public AcceptedChallengeDTO toAcceptedChallengeDTO(ChallengeProgression challengeProgression) {

        return new AcceptedChallengeDTO(
                this.toChallengeDTO(challengeProgression.getChallenge()), challengeProgression.getProgress());
    }

    public List<AcceptedChallengeDTO> toAcceptedChallengeDTO(List<ChallengeProgression> challengeProgressions) {

        List<AcceptedChallengeDTO> acceptedChallengesDTO = new ArrayList<>();

        for (ChallengeProgression cp: challengeProgressions) {

            acceptedChallengesDTO.add(this.toAcceptedChallengeDTO(cp));

        }

        return acceptedChallengesDTO;
    }
}

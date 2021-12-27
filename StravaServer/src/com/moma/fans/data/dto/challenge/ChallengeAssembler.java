package com.moma.fans.data.dto.challenge;

import com.moma.fans.data.domain.*;

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

        if (challenge instanceof TimeChallenge) {
            challengeDTO.setTimeToAchieve(((TimeChallenge) challenge).getTimeToAchieve());
        }

        else if (challenge instanceof  DistanceChallenge) {
            challengeDTO.setDistanceToAchieve(((DistanceChallenge) challenge).getDistanceToAchieve());
        }

        return challengeDTO;
    }

    public List<ChallengeDTO> toChallengeDTO(List<Challenge> challenges) {

        List<ChallengeDTO> challengesDTO = new ArrayList<>();

        for (Challenge ch: challenges) {

            challengesDTO.add(this.toChallengeDTO(ch));
        }

        return challengesDTO;
    }

    public Challenge toChallenge(ChallengeDTO challengeDTO) {

        Challenge challenge;

        if (challengeDTO.getDistanceToAchieve() > 0) {

            challenge = new DistanceChallenge(challengeDTO.getDistanceToAchieve());
        }

        else {

            challenge = new TimeChallenge(challengeDTO.getTimeToAchieve());
        }

       challenge.setTitle(challengeDTO.getTitle());
       challenge.setSport(Sport.valueOfSport(challengeDTO.getSport()));
       challenge.setStartDate(challengeDTO.getStartDate());
       challenge.setEndDate(challengeDTO.getEndDate());

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

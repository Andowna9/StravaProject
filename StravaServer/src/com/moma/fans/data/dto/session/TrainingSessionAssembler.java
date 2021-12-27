package com.moma.fans.data.dto.session;

import com.moma.fans.data.domain.Sport;
import com.moma.fans.data.domain.TrainingSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.TrainingSession}
 * a objetos {@link TrainingSessionDTO}.
 * Implementa el patrón de diseño Singleton.
 * @author JonanC
 * @author Julen
 */
public class TrainingSessionAssembler {

    private TrainingSessionAssembler() { }

    // Singleton on demand
    private static class InstanceHolder {
        private static final TrainingSessionAssembler INSTANCE = new TrainingSessionAssembler();
    }

    public static TrainingSessionAssembler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public TrainingSessionDTO toTrainingSessionDTO(TrainingSession trainingSession) {

        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();

        trainingSessionDTO.setTitle(trainingSession.getTitle());
        trainingSessionDTO.setSport(trainingSession.getSport().toString());
        trainingSessionDTO.setDateTime(trainingSession.getDate());
        trainingSessionDTO.setDistance(trainingSession.getDistance());
        trainingSessionDTO.setDuration(trainingSession.getDuration());

        return trainingSessionDTO;

    }

    public List<TrainingSessionDTO> toTrainingSessionDTO(List<TrainingSession> trainingSessions) {

        List<TrainingSessionDTO> trainingSessionsDTO = new ArrayList<>();

        for (TrainingSession ts: trainingSessions) {

            trainingSessionsDTO.add(this.toTrainingSessionDTO(ts));
        }

        return trainingSessionsDTO;
    }
    
    public TrainingSession toTrainingSession(TrainingSessionDTO trainingSession) {

        return new TrainingSession(trainingSession.getTitle(),Sport.valueOfSport(trainingSession.getSport()) ,trainingSession.getDistance() , trainingSession.getDateTime(), trainingSession.getDuration());

    }
}

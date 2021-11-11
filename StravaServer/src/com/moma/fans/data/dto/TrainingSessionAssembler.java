package com.moma.fans.data.dto;

import com.moma.fans.data.domain.Sport;
import com.moma.fans.data.domain.TrainingSession;
import com.moma.fans.data.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.TrainingSession}
 * a objetos {@link TrainingSessionDTO}.
 * @author JonanC
 * @author Julen
 */
public class TrainingSessionAssembler {

    public TrainingSessionDTO toDTO(TrainingSession trainingSession) {

        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();

        trainingSessionDTO.setTitle(trainingSession.getTitle());
        trainingSessionDTO.setSport(trainingSession.getSport().toString());
        trainingSessionDTO.setDate(trainingSession.getDate());
        trainingSessionDTO.setDistance(trainingSession.getDistance());
        trainingSessionDTO.setDuration(trainingSession.getDuration());

        return trainingSessionDTO;

    }

    public List<TrainingSessionDTO> toDTO(List<TrainingSession> trainingSessions) {

        List<TrainingSessionDTO> trainingSessionsDTO = new ArrayList<>();

        for (TrainingSession ts: trainingSessions) {

            trainingSessionsDTO.add(this.toDTO(ts));
        }

        return trainingSessionsDTO;
    }
    
    public TrainingSession toTrainingSession(TrainingSessionDTO trainingSession) {

        return new TrainingSession(trainingSession.getTitle(),Sport.valueOf(trainingSession.getSport()) ,trainingSession.getDistance() , trainingSession.getDateTime(), trainingSession.getDuration());

    }
}

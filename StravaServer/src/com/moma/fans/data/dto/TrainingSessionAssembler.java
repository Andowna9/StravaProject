package com.moma.fans.data.dto;

import com.moma.fans.data.domain.TrainingSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.TrainingSession}
 * a objetos {@link TrainingSessionDTO}.
 * @author JonanC
 */
public class TrainingSessionAssembler {

    public TrainingSessionDTO trainingSessionToDTO(TrainingSession trainingSession) {

        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();

        trainingSessionDTO.setTitle(trainingSession.getTitle());
        trainingSessionDTO.setSport(trainingSession.getSport().toString());
        trainingSessionDTO.setDate(trainingSession.getDate());
        trainingSessionDTO.setDistance(trainingSession.getDistance());
        trainingSessionDTO.setDuration(trainingSession.getDuration());

        return trainingSessionDTO;

    }

    public List<TrainingSessionDTO> trainingSessionsToDTO(List<TrainingSession> trainingSessions) {

        List<TrainingSessionDTO> trainingSessionsDTO = new ArrayList<>();

        for (TrainingSession ts: trainingSessions) {

            trainingSessionsDTO.add(this.trainingSessionToDTO(ts));
        }

        return trainingSessionsDTO;
    }
}

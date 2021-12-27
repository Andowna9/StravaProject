package com.moma.fans.data.dto.challenge;

/**
 * Clase que representa el objeto de transferencia para
 * un reto aceptado.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 */
public class AcceptedChallengeDTO extends ChallengeDTO {

    private double progress;

    // Default constructor

    public AcceptedChallengeDTO() {

        super();
        progress = 0;
    }

    public AcceptedChallengeDTO(ChallengeDTO challengeDTO, double progress) {

        super(challengeDTO);
        this.progress = progress;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }


}

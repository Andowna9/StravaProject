package com.moma.fans.data.dto.challenge;

/**
 * Clase que representa el objeto de transferencia para
 * un reto aceptado.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 */
public class AcceptedChallengeDTO extends ChallengeDTO {

    private double progress;

    // Implicit default constructor

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }


}

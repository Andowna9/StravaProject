package com.moma.fans.data.domain;

/**
 * Clase contenedora para almacenar el progreso acumulado de un
 * reto y la referencia al reto en cuestión.
 * @see Challenge
 * @author JonanC
 */
public class ChallengeProgression {

    private Challenge challenge;
    private double progress;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}

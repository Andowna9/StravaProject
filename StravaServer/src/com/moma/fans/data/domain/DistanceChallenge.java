package com.moma.fans.data.domain;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import java.time.LocalDate;

/**
 * Clase que representa un reto de distancia.
 * @see Challenge
 * @author JonanC
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class DistanceChallenge extends Challenge {

    private double distanceToAchieve;

    public DistanceChallenge(String title, LocalDate startDate, LocalDate endDate, Sport sport, double distanceToAchieve) {
        super(title, startDate, endDate, sport);
        this.distanceToAchieve = distanceToAchieve;
    }

    public DistanceChallenge(double distanceToAchieve) {

        super();
        this.distanceToAchieve = distanceToAchieve;
    }

    @Override
    public double updateProgress(TrainingSession tr) {

        return tr.getDistance() / distanceToAchieve;
    }

    public double getDistanceToAchieve() {
        return distanceToAchieve;
    }

    @Override
    public String getObjective() {

        return "Recorrer " + distanceToAchieve + " km";
    }
}

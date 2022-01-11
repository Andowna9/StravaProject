package com.moma.fans.data.domain;

import javax.jdo.annotations.PersistenceCapable;

import java.time.Duration;
import java.time.LocalDate;

@PersistenceCapable(detachable = "true")
public class TimeChallenge extends Challenge {

    private Duration timeToAchieve;

    public TimeChallenge(String title, LocalDate startDate, LocalDate endDate, Sport sport, Duration timeToAchieve) {
        super(title, startDate, endDate, sport);
        this.timeToAchieve = timeToAchieve;
    }

    public Duration getTimeToAchieve() {
        return timeToAchieve;
    }

    public TimeChallenge(Duration timeToAchieve) {

        super();
        this.timeToAchieve = timeToAchieve;
    }

    @Override
    public double updateProgress(TrainingSession tr) {

        return tr.getDuration().toMillis() / (double) timeToAchieve.toMillis();
    }

    @Override
    public String getObjective() {

        long HH = timeToAchieve.toHours();
        long mm = timeToAchieve.toMinutesPart();
        return "Entrenar durante " + HH + " h " + mm + " min";
    }


}

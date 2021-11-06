package com.moma.fans.data.domain;

import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa un reto deportivo.
 * @author JonanC
 */
public class Challenge {

    private String title;
    private Sport sport;
    private double distanceToAchieve;
    private Duration timeToAchieve;
    private Date startDate;
    private Date endDate;

    private User creator;

    // Métodos principales

    // TODO

    // Getters y setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public double getDistanceToAchieve() {
        return distanceToAchieve;
    }

    public void setDistanceToAchieve(double distanceToAchieve) {
        this.distanceToAchieve = distanceToAchieve;
    }

    public Duration getTimeToAchieve() {
        return timeToAchieve;
    }

    public void setTimeToAchieve(Duration timeToAchieve) {
        this.timeToAchieve = timeToAchieve;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

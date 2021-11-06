package com.moma.fans.data.domain;

import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa una sesión de entrenamiento de un deporte.
 * @author JonanC
 */
public class TrainingSession {

    private String title;
    private Sport sport;
    private double distance;
    private Date date;
    private Duration duration;

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}

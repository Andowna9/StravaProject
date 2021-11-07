package com.moma.fans.data.dto;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa el objeto de transferencia para
 * {@link com.moma.fans.data.domain.TrainingSession}.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 */
public class TrainingSessionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String sport;
    private double distance;
    private Date date;
    private Duration duration;

    // Implicit default constructor

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
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

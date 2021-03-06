package com.moma.fans.data.dto.session;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Clase que representa el objeto de transferencia para
 * {@link com.moma.fans.data.domain.TrainingSession}.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 * @author Julen
 */
public class TrainingSessionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String sport;
    private double distance;
    private LocalDateTime dateTime;
    private Duration duration;

    // Default constructor

    public TrainingSessionDTO() {

        title = "";
        sport = "Running";
        distance = 0;
        dateTime = LocalDateTime.MIN;
        duration = Duration.ZERO;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}

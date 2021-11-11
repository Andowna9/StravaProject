package com.moma.fans.data.domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Clase que representa una sesión de entrenamiento de un deporte.
 * @author JonanC
 * @author Julen
 */
public class TrainingSession {

    private String title;
    private Sport sport;
    private double distance;
    private LocalDateTime dateTime;
    private Duration duration;

    // Métodos principales

    // TODO
    
    public TrainingSession(String title, Sport sport, double distance, LocalDateTime dateTime, Duration duration) {
		super();
		this.title = title;
		this.sport = sport;
		this.distance = distance;
		this.dateTime = dateTime;
		this.duration = duration;
	}
    

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

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}

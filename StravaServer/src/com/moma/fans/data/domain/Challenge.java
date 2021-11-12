package com.moma.fans.data.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un reto deportivo.
 * @author JonanC
 * @author AlexNitu
 */
public class Challenge {

    private int id;
    private String title;
    private Sport sport;
    private double distanceToAchieve;
    private Duration timeToAchieve;
    private LocalDate startDate;
    private LocalDate endDate;
    private State

    private User creator;

    // Constructor

    public Challenge(String title, LocalDate startDate, LocalDate endDate, double distanceToAchieve, Duration timeToAchieve, Sport sport){

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distanceToAchieve = distanceToAchieve;
        this.timeToAchieve = timeToAchieve;
        this.sport = sport;

    }

    // Métodos principales

    // TODO

    // Getters y setters

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

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

    public void setDistanceToAchieve(double distance) {
        this.distanceToAchieve = distance;
    }

    public Duration getTimeToAchieve() {
        return timeToAchieve;
    }

    public void setTimeToAchieve(Duration timeToAchieve) {
        this.timeToAchieve = timeToAchieve;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCreator(User creator) {

        this.creator = creator;
    }

    public User getCreator() {

        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return id == challenge.id && Objects.equals(title, challenge.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}

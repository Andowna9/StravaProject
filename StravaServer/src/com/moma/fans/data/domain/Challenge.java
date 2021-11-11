package com.moma.fans.data.domain;

import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa un reto deportivo.
 * @author JonanC
 */
public class Challenge {

    private int id;
    private String name;
    private Sport sport;
    private Double distance;
    private Duration timeToAchieve;
    private Date startDate;
    private Date endDate;

    private User creator;

    // Constructores
    public Challenge(int id, String name, Date startDate, Date endDate, double distance, Duration timeToAchieve, Sport sport){

        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.distance = distance;
        this.timeToAchieve = timeToAchieve;
        this.sport = sport;

    }

    public Challenge(){
        this.id = 0;
        this.name = "";
        this.startDate = new Date();
        this.endDate = new Date();
        this.distance = 0.0;
        this.timeToAchieve = Duration.ZERO;
        this.sport = Sport.RUNNING;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

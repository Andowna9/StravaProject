package com.moma.fans.data.dto.challenge;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class ChallengeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String sport;
    private double distanceToAchieve;
    private Duration timeToAchieve;
    private LocalDate startDate;
    private LocalDate endDate;

    // Default constructor

    public ChallengeDTO() {

        title = "";
        sport = "Running";
        distanceToAchieve = 0;
        timeToAchieve = Duration.ZERO;
        startDate = LocalDate.EPOCH;
        endDate = LocalDate.EPOCH;

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

}

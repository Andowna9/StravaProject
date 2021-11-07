package com.moma.fans.data.dto;

import com.moma.fans.data.domain.Sport;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa el objeto de transferencia para
 * {@link com.moma.fans.data.domain.Challenge}.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 */
public class ChallengeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String sport;
    private double distanceToAchieve;
    private Duration timeToAchieve;
    private Date startDate;
    private Date endDate;
    private double progress;

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

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}

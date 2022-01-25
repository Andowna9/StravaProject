package com.moma.fans.data.dto.challenge;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa el objeto de transferencia para
 * un reto no aceptado.
 * Implementa el patrón de diseño DAO.
 * @author JonanC
 */
public class ChallengeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String sport;
    private LocalDate startDate;
    private LocalDate endDate;
    private String goal;

    // Default constructor

    public ChallengeDTO() {

        title = "";
        sport = "Running";
        startDate = LocalDate.EPOCH;
        endDate = LocalDate.EPOCH;
        goal = "";

    }

    public ChallengeDTO(ChallengeDTO challengeDTO) {

        title = challengeDTO.title;
        sport = challengeDTO.sport;
        startDate = challengeDTO.startDate;
        endDate = challengeDTO.endDate;
        goal = challengeDTO.goal;
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

package com.moma.fans.data.dto.challenge;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa el objeto de transferencia para
 * la creación de un reto.
 * Implementa el patrón de diseño DAO.
 * @author JonanC
 */
public class ChallengeCreationDTO implements Serializable {

    private String title;
    private String sport;
    private LocalDate startDate;
    private LocalDate endDate;

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
}

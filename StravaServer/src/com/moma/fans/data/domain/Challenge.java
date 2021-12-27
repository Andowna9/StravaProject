package com.moma.fans.data.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un reto deportivo.
 * @author JonanC
 * @author AlexNitu
 */
public abstract class Challenge {

    protected int id;
    protected String title;
    protected Sport sport;
    protected LocalDate startDate;
    protected LocalDate endDate;

    protected User creator;

    // Constructor

    public Challenge(String title, LocalDate startDate, LocalDate endDate, Sport sport){

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sport = sport;

    }

    public Challenge() { }

    /**
     * Calcula el valor del porcentaje de un reto aceptado
     * de acuerdo a una sesión de entrenamiento dada.
     * @param tr Sesión de entrenamiento
     * @return porcentaje del reto cubierto
     */
    public abstract double updateProgress(TrainingSession tr);
    public abstract String getObjective();

    // Métodos principales

    public boolean hasEnded() {

        return LocalDate.now().isAfter(this.endDate);
    }

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

package com.moma.fans.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un usuario.
 * @author JonanC
 */
public class User {

    private String nickname;
    private String email;
    private String password;
    private Date birthDate;
    private float weight;
    private float height;
    private short minHeartRate;
    private short maxHeartRate;

    List<TrainingSession> trainingSessions = new ArrayList<>();

    List<Challenge> createdChallenges = new ArrayList<>();
    List<Challenge> acceptedChallenges = new ArrayList<>();

    // Métodos principales

    /**
     * Permite validar el acceso a una cuenta de usuario.
     * @param password contraseña introducida por el usuario
     * @return true si las contraseñas coinciden, false en caso contrario
     */
    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }

    // TODO

    // Getters y setters

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public short getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(short minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public short getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(short maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }
}

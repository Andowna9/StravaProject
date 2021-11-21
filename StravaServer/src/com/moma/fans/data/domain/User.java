package com.moma.fans.data.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un usuario génerico sin contraseña
 * (usuario de Google o Facebook).
 * @author JonanC
 * @author AlexNitu
 */
public class User {

    private String email;
    private String nickname;
    private RegisterType registerType;
    private LocalDate birthDate;
    private float height;
    private float weight;
    private short minHeartRate;
    private short maxHeartRate;

    // Sesiones de entrenamiento
    List<TrainingSession> trainingSessions = new ArrayList<>();

    // Retos
    List<Challenge> createdChallenges = new ArrayList<>();
    List<Challenge> acceptedChallenges = new ArrayList<>();

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    // Métodos principales

    public void addTrainingSession(TrainingSession tr) {

        trainingSessions.add(tr);
    }

    public void addAcceptedChallenge(Challenge challenge){

        acceptedChallenges.add(challenge);

    }

    public void addCreatedChallenge(Challenge challenge) {

        createdChallenges.add(challenge);
    }



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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public List<TrainingSession> getTrainingSessions() {

        return trainingSessions;
    }

    public List<Challenge> getAcceptedChallenges() {

        return acceptedChallenges;
    }

    public List<Challenge> getCreatedChallenges() {

        return createdChallenges;
    }

    // Equals y Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}

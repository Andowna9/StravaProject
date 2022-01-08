package com.moma.fans.data.domain;

import javax.jdo.annotations.*;

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

@PersistenceCapable(detachable = "true")
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class User {

    private String email;
    private String nickname;
    private RegisterType registerType;
    private LocalDate birthDate;
    private float height;
    private float weight;
    private short minHeartRate;
    private short maxHeartRate;

    // Sesiones de entrenamiento (1-N Unidireccional)
    @Persistent(dependentElement = "true", defaultFetchGroup = "true")
    @Join
    List<TrainingSession> trainingSessions = new ArrayList<>();

    // Retos creados (1-N Bidireccional)
    @Persistent(mappedBy = "creator", dependentElement = "true", defaultFetchGroup = "true")
    @Join
    List<Challenge> createdChallenges = new ArrayList<>();

    // Retos aceptados (N-M)
    @Persistent(table = "USERS_CHALLENGES", defaultFetchGroup = "true")
    @Join(column = "USER_ID")
    @Element(column = "CHALLENGE_ID")
    @Order(extensions = @Extension(vendorName = "datanucleus", key = "list-ordering", value = "id ASC"))
    List<Challenge> acceptedChallenges = new ArrayList<>();

    public User(RegisterType registerType, String email, String nickname) {

        this.registerType = registerType;
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

    public RegisterType getRegisterType() {
        return registerType;
    }

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

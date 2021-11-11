package com.moma.fans.data.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un usuario.
 * @author JonanC
 */
public class User {

    private String nickname;
    private String email;
    private String password;
    private LocalDate birthDate;
    private float weight;
    private float height;
    private short minHeartRate;
    private short maxHeartRate;

    List<TrainingSession> trainingSessions = new ArrayList<>();

    List<Challenge> createdChallenges = new ArrayList<>();
    List<Challenge> acceptedChallenges = new ArrayList<>();

    public User(String nickname, String email, String password, LocalDate birthDate, float weight, float height, short minHeartRate, short maxHeartRate) {
        this.nickname = nickname;
        this.email = email;
        this.password = this.encrypt(password);
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.minHeartRate = minHeartRate;
        this.maxHeartRate = maxHeartRate;
    }

    // Métodos principales

    /**
     * Permite validar el acceso a una cuenta de usuario.
     * @param password contraseña introducida por el usuario
     * @return true si las contraseñas coinciden, false en caso contrario
     */
    public boolean isPasswordValid(String password) {

        return this.encrypt(password).equals(this.password);
    }

    /**
     * Encripta una contraseña.
     * @param password contraseña en texto plano
     * @return contraseña encriptada
     */
    public String encrypt(String password) {

        String generatedPassword = null;

        // Lógica de encriptación
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte [] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            // Formato hexadecimal
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;

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

    public void setPassword(String password) {
        this.password = password;
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

    public boolean acceptChallenge(Challenge challenge){
        try {
            acceptedChallenges.add(challenge);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Challenge> checkAcceptedChallenges(){
        return (ArrayList<Challenge>) acceptedChallenges;
    }


}

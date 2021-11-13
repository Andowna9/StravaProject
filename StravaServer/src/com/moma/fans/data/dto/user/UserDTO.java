package com.moma.fans.data.dto.user;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa el objeto de transferencia para
 * {@link com.moma.fans.data.domain.User}.
 * Implementa el patrón de diseño DTO.
 * @author JonanC
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String email;
    private LocalDate birthDate;
    private float weight;
    private float height;
    private short minHeartRate;
    private short maxHeartRate;

    // Implicit default constructor

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
}

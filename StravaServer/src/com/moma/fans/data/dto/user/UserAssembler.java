package com.moma.fans.data.dto.user;

import com.moma.fans.data.domain.User;
import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.data.dto.user.UserDTO;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * usuario.
 * @see ProfileCreationDTO
 * @see UserDTO
 * @author JonanC
 */
public class UserAssembler {

    public UserDTO toDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setWeight(user.getWeight());
        userDTO.setHeight(user.getHeight());
        userDTO.setMinHeartRate(user.getMinHeartRate());
        userDTO.setMaxHeartRate(user.getMaxHeartRate());

        return userDTO;
    }

    public void createProfile(User user, ProfileCreationDTO userDTO) {

        user.setBirthDate(userDTO.getBirthDate());
        user.setHeight(userDTO.getHeight());
        user.setWeight(userDTO.getWeight());
        user.setMinHeartRate(userDTO.getMinHeartRate());
        user.setMaxHeartRate(userDTO.getMaxHeartRate());
    }
}
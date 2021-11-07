package com.moma.fans.data.dto;

import com.moma.fans.data.domain.User;

/**
 * Clase de utilidad que forma parte del patrón DTO.
 * Se encarga de la conversión de objetos
 * {@link com.moma.fans.data.domain.User}
 * a objetos {@link UserDTO}.
 * @author JonanC
 */
public class UserAssembler {

    public UserDTO userToDTO(User user) {

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
}

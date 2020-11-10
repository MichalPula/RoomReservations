package com.Pulson.RoomReservations.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadUserMapper {

    public List<UserReadDTO> mapToUserReadDTOsList(List<User> users) {
        List<UserReadDTO> userReadDTOList = new ArrayList<>();

        users.forEach(user -> {
            UserReadDTO userReadDTO = new UserReadDTO();
            userReadDTO.setId(user.getId());
            userReadDTO.setFirstName(user.getFirstName());
            userReadDTO.setLastName(user.getLastName());
            userReadDTO.setPhoneNumber(user.getPhoneNumber());
            userReadDTO.setUsername(user.getUsername());
            userReadDTOList.add(userReadDTO);
        });
        return userReadDTOList;
    }
}

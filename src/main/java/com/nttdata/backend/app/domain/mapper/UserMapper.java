package com.nttdata.backend.app.domain.mapper;

import com.nttdata.backend.app.domain.dto.UserDTO;

import com.nttdata.backend.app.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public  interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDTO userToUserDTO(User user);

    public User userDTOToUser(UserDTO userDTO) ;

}

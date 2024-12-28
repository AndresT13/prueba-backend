package com.nttdata.backend.app.ports.in;

import com.nttdata.backend.app.domain.dto.UserDTO;


import java.util.List;

public interface UserPort {

    List<UserDTO> getAllUsers();

    UserDTO getUserbyId(Long id);

    UserDTO createUSer(UserDTO userDTO);

    void deleteUser(Long id);
}

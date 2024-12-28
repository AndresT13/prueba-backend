package com.nttdata.backend.app.adapters.in.web;

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.ports.in.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserPort userPort;

    @Autowired
    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userPort.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userPort.getUserbyId(id);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userPort.createUSer(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userPort.deleteUser(id);
    }
}

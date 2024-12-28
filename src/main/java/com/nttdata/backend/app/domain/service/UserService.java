package com.nttdata.backend.app.domain.service;

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.domain.mapper.UserMapper;
import com.nttdata.backend.app.domain.model.User;
import com.nttdata.backend.app.exception.BadRequestException;
import com.nttdata.backend.app.exception.InternalServerException;
import com.nttdata.backend.app.exception.NotFoundException;
import com.nttdata.backend.app.ports.in.UserPort;
import com.nttdata.backend.app.ports.out.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserPort {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService( UserRepository userRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }



    @Override
    public List<UserDTO> getAllUsers() {
        try{
            logger.info("Fetching all users");
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(userMapper::userToUserDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to fetch users", e);
            throw new InternalServerException("Failed to fetch users.");
        }
    }

    @Override
    public UserDTO getUserbyId(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)

                .orElseThrow(() -> {
                    logger.warn("User not found with id: {}", id);

                    return new NotFoundException("User not found with id: " + id);
                });
    }

    @Override
    public UserDTO createUSer(UserDTO userDTO) {
        try{
            logger.info("Creating user: {}", userDTO);
            User user = userMapper.userDTOToUser(userDTO);
            User savedUser = userRepository.save(user);

            return userMapper.userToUserDTO(savedUser);
        }catch (Exception e) {
            logger.error("Failed to create user: {}", userDTO, e);
            throw new BadRequestException("Failed to create user."); }

    }

    @Override
    public void deleteUser(Long id) {
        try {
            logger.info("Deleting user with id: {}", id);
            if (!userRepository.existsById(id)) {
                logger.warn("User not found with id: {}", id);
                throw new NotFoundException("User not found with id: " + id);
            }
            userRepository.deleteById(id);
            logger.info("Deleted user with id: {}", id);
        } catch (Exception e) {
            logger.error("Failed to delete user with id: {}", id, e);
            throw new InternalServerException("Failed to delete user."); } }

}

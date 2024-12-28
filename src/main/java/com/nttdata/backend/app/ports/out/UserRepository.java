package com.nttdata.backend.app.ports.out;



import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.domain.model.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);
}

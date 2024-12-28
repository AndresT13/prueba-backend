package com.nttdata.backend.app.adapters.out.persistence;


import com.nttdata.backend.app.domain.model.User;
import com.nttdata.backend.app.ports.out.UserRepository;
import com.nttdata.backend.app.ports.out.persistence.IDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserPersistenceAdapter implements UserRepository {


    private final IDataUserRepository dataUserRepository;


    @Autowired
    public UserPersistenceAdapter(IDataUserRepository dataUserRepository){
        this.dataUserRepository = dataUserRepository;
    }

    @Override
    public List<User> findAll() {
        return dataUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return dataUserRepository.findById(id);
    }


    @Override
    public User save(User user) {
        return dataUserRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        dataUserRepository.deleteById(id);
    }

    @Override public
    boolean existsById(Long id)
    { return dataUserRepository.existsById(id); }
}

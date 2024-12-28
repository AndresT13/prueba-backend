package com.nttdata.backend.app.ports.out.persistence;

import com.nttdata.backend.app.domain.model.User;
import com.nttdata.backend.app.ports.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

  private final IDataUserRepository dataUserRepository;

  @Autowired
  public UserRepositoryImpl(IDataUserRepository dataUserRepository){
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

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}

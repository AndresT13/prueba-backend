package com.nttdata.backend.app.ports.out.persistence;


import com.nttdata.backend.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDataUserRepository  extends JpaRepository<User, Long> {
}

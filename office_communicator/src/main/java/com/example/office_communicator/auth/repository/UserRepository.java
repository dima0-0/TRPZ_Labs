package com.example.office_communicator.auth.repository;

import com.example.office_communicator.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User, Integer> {
    @Override
    <S extends User> S save(S entity);

    Optional<User> findByEmail(String email);
}
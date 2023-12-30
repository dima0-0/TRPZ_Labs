package com.example.office_communicator.auth.repository;

import com.example.office_communicator.auth.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Override
    Optional<Role> findById(Integer integer);

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
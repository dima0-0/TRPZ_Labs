package com.example.office_communicator.auth.mapper;

import com.example.office_communicator.auth.dto.RegisterRequest;
import com.example.office_communicator.auth.dto.UserDto;
import com.example.office_communicator.auth.entity.Role;
import com.example.office_communicator.auth.entity.User;
import com.example.office_communicator.auth.repository.RoleRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected RoleRepository roleRepository;


    public abstract User registerRequestToUser(RegisterRequest registerRequest);
    public abstract UserDto userToUserDto(User user);
    @AfterMapping
    protected void encodePassword(RegisterRequest registerRequest,@MappingTarget User.UserBuilder user) {
        user.password(passwordEncoder.encode(registerRequest.getPassword()));
    }

    @AfterMapping
    protected void setDefaultRole(@MappingTarget User.UserBuilder user) {
        user.userRoles(Set.of(roleRepository.findByName("ROLE_USER")
                .orElse(Role.builder().name("ROLE_USER").build())));
    }
}
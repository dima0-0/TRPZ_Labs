package com.example.office_communicator.auth.rolesSetup;


import com.example.office_communicator.auth.entity.Role;
import com.example.office_communicator.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRolesIfNotFound();
    }


    public void createRolesIfNotFound() {
        List<Role> roles = getSampleRoles();
        roles.stream()
                .filter(role -> !roleRepository.existsByName(role.getName()))
                .forEach(role -> {
                    roleRepository.save(role);
                    log.warn("Role {} created", role.getName());
                });
    }

    private List<Role> getSampleRoles() {
        return List.of(
                new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")
        );
    }
}

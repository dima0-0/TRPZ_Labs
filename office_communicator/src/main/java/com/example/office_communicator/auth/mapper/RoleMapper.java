package com.example.office_communicator.auth.mapper;

import com.example.office_communicator.auth.dto.RoleDto;
import com.example.office_communicator.auth.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToDto(Role source);
}

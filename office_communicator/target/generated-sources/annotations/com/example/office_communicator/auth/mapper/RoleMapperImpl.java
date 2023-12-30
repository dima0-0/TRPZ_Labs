package com.example.office_communicator.auth.mapper;

import com.example.office_communicator.auth.dto.RoleDto;
import com.example.office_communicator.auth.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T10:54:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto roleToDto(Role source) {
        if ( source == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.name( source.getName() );

        return roleDto.build();
    }
}

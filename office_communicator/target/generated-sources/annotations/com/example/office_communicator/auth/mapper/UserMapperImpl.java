package com.example.office_communicator.auth.mapper;

import com.example.office_communicator.auth.dto.RegisterRequest;
import com.example.office_communicator.auth.dto.RoleDto;
import com.example.office_communicator.auth.dto.UserDto;
import com.example.office_communicator.auth.entity.Role;
import com.example.office_communicator.auth.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T10:54:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User registerRequestToUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.password( registerRequest.getPassword() );
        user.dateOfBirthday( registerRequest.getDateOfBirthday() );
        user.gender( registerRequest.getGender() );
        user.phoneNumber( registerRequest.getPhoneNumber() );
        user.name( registerRequest.getName() );
        user.surname( registerRequest.getSurname() );
        user.email( registerRequest.getEmail() );

        encodePassword( registerRequest, user );
        setDefaultRole( user );

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.userRoles( roleSetToRoleDtoSet( user.getUserRoles() ) );
        userDto.password( user.getPassword() );
        userDto.dateOfBirthday( user.getDateOfBirthday() );
        userDto.gender( user.getGender() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.name( user.getName() );
        userDto.surname( user.getSurname() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.name( role.getName() );

        return roleDto.build();
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDto( role ) );
        }

        return set1;
    }
}

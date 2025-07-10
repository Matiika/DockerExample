package com.AstronSpringHomework.App.mapper;

import com.AstronSpringHomework.App.dto.userDto.UserCreateDTO;
import com.AstronSpringHomework.App.dto.userDto.UserDTO;
import com.AstronSpringHomework.App.dto.userDto.UserUpdateDTO;
import com.AstronSpringHomework.App.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T21:53:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Homebrew)"
)
@Component
public class UserMapStructMapperImpl implements UserMapStructMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setAge( user.getAge() );
        userDTO.setCreatedAt( user.getCreatedAt() );

        return userDTO;
    }

    @Override
    public User fromCreateDTO(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setAge( dto.getAge() );

        return user;
    }

    @Override
    public void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getAge() != null ) {
            user.setAge( dto.getAge() );
        }
    }
}

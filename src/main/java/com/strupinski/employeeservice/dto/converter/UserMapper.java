package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.UserDTO;
import com.strupinski.employeeservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "authority", ignore = true)
    User fromDto(UserDTO user);

    @Mapping(target = "authority", ignore = true)
    UserDTO toDto(User user);
}

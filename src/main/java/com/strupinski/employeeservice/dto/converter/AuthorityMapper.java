package com.strupinski.employeeservice.dto.converter;

import com.strupinski.employeeservice.dto.AuthorityDTO;
import com.strupinski.employeeservice.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    @Mapping(target = "users", ignore = true)
    Authority fromDto(AuthorityDTO authorityDTO);

    @Mapping(target = "users", ignore = true)
    AuthorityDTO toDto(Authority tag);
}

package com.forero.sunbelt.infraestructure.mapper;

import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.services.dao.UserDao;
import com.forero.sunbelt.openapi.api.model.UserRequestDto;
import com.forero.sunbelt.openapi.api.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "secondSurname", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "secondName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    User toModel(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);

    @Mapping(target = "secondName", source = "secondName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phone", source = "phone")
    User entityToModel(UserDao userDao);
}

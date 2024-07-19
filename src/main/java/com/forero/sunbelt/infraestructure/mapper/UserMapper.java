package com.forero.sunbelt.infraestructure.mapper;

import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.services.dto.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "secondSurname", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "secondName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    User toModel(UserRequestDto userRequestDto);
}

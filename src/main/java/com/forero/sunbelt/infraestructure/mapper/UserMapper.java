package com.forero.sunbelt.infraestructure.mapper;

import com.forero.sunbelt.domain.model.DocumentType;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.services.dao.UserEntity;
import com.forero.sunbelt.openapi.api.model.UserRequestDto;
import com.forero.sunbelt.openapi.api.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "secondSurname", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "secondName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "mapStringToDocumentType")
    User toModel(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);

    @Mapping(target = "city", source = "city")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "secondSurname", source = "secondSurname")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "secondName", source = "secondName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "documentNumber", source = "documentNumber")
    User toModelEntity(UserEntity user);

    @Named("mapStringToDocumentType")
    default DocumentType mapStringToDocumentType(String documentType) {
        if (documentType == null) {
            return null;
        }
        return DocumentType.valueOf(documentType.toUpperCase());
    }
}

package com.forero.sunbelt.infraestructure.mapper;

import com.forero.sunbelt.application.exception.DocumentTypeException;
import com.forero.sunbelt.domain.exception.CodeException;
import com.forero.sunbelt.domain.model.DocumentType;
import com.forero.sunbelt.domain.model.User;
import com.forero.sunbelt.infraestructure.services.entity.UserEntity;
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
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "mapStringToDocumentType")
    User toModel(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);

    User toModelEntity(UserEntity user);

    @Named("mapStringToDocumentType")
    default DocumentType mapStringToDocumentType(String documentType) {
        if (documentType == null) {
            return null;
        }

        try {
            return DocumentType.valueOf(documentType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DocumentTypeException(CodeException.INVALID_TYPE_DOCUMENT, null);
        }
    }
}

package com.cooksys.server.mappers;

import org.mapstruct.Mapper;
import com.cooksys.server.entities.Credential;
import com.cooksys.server.models.CredentialDto;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

    Credential dtoToEmbeddable(CredentialDto credentialDto);


}

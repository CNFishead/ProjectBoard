package com.cooksys.server.mappers;

import com.cooksys.server.entities.Profile;
import com.cooksys.server.models.ProfileDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    
    ProfileDto embeddableToDto(Profile profile);

    Profile dtoToEmbeddable(ProfileDto profileResponseDto);

    
}

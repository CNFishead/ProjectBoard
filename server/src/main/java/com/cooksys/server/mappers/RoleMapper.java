package com.cooksys.server.mappers;

import com.cooksys.server.entities.Role;
import com.cooksys.server.models.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto entityToDto(Role role);
    
    Role DtoToEntity(RoleDto roleDTO);

}

package com.cooksys.server.mappers;

import com.cooksys.server.entities.Team;
import com.cooksys.server.models.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProjectMapper.class, UserMapper.class })
public interface TeamMapper {

    TeamDto entityToDto(Team team);

    List<TeamDto> entitiesToDtos(List<Team> teams);

    @Mapping(target = "deleted", ignore = true)
	Team DtoToEntity(TeamDto team);

}

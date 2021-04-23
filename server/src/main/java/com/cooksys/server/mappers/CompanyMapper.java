package com.cooksys.server.mappers;

import com.cooksys.server.entities.Company;
import com.cooksys.server.models.CompanyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto entityToDto(Company company);

    List<CompanyDto> entitiesToDtos(List<Company> companies);

}

package br.com.bancointer.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.bancointer.DTO.DigitoUnicoDTO;
import br.com.bancointer.entity.DigitoUnico;

@Mapper(componentModel = "spring")
public interface DigitoMapper {
	 
	DigitoMapper INSTANCE = Mappers.getMapper( DigitoMapper.class ); 
 
	@InheritInverseConfiguration
	
	@org.mapstruct.Named("noUsuario")
	@Mapping(target = "usuario", ignore = true)
    DigitoUnicoDTO digitoUnicoToDto(DigitoUnico digitoUnico, @Context CycleAvoidingMappingContext context); 
}

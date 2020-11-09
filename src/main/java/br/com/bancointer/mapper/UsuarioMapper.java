package br.com.bancointer.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.bancointer.DTO.UsuarioDTO;
import br.com.bancointer.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	 
	UsuarioMapper INSTANCE = Mappers.getMapper( UsuarioMapper.class ); 
 
//	@Mapping(target = "usuario.digitos", ignore=true)
	@Mapping(target ="usuario.digitos", qualifiedByName="noUsuario")
//	@InheritInverseConfiguration
    UsuarioDTO usuarioToDto(Usuario usuario, @Context CycleAvoidingMappingContext context);
	
//	@Mapping(target ="usuario.digitos", qualifiedByName="noUsuario")
//	UsuarioDTO usuarioToDto(Usuario usuario);
    
    Usuario usuarioDtoToUsuario(UsuarioDTO usuarioDto, @Context CycleAvoidingMappingContext context);
}

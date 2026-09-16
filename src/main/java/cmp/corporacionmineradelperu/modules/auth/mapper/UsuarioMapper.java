package cmp.corporacionmineradelperu.modules.auth.mapper;

import cmp.corporacionmineradelperu.modules.auth.dto.AuthResponse;
import cmp.corporacionmineradelperu.modules.auth.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "token", source = "jwtToken")
    @Mapping(target = "rol", expression = "java(usuario.getRol().name())")
    AuthResponse toAuthResponse(Usuario usuario, String jwtToken);
}

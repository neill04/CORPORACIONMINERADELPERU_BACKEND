package cmp.corporacionmineradelperu.modules.personal.mapper;

import cmp.corporacionmineradelperu.modules.auth.entity.Rol;
import cmp.corporacionmineradelperu.modules.auth.entity.Usuario;
import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioRequest;
import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toUsuarioResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "passwordEncriptado")
    @Mapping(target = "rol", source = "rolAsignado")
    @Mapping(target = "activo", constant = "true")
    Usuario toUsuario(UsuarioRequest request, String passwordEncriptado, Rol rolAsignado);
}

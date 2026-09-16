package cmp.corporacionmineradelperu.modules.personal.dto;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nombres,
        String apellidos,
        String email,
        String rol,
        boolean activo
) {}
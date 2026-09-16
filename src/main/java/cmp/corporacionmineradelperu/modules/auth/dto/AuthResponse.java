package cmp.corporacionmineradelperu.modules.auth.dto;

public record AuthResponse(
        String token,
        String nombres,
        String apellidos,
        String rol
) {}

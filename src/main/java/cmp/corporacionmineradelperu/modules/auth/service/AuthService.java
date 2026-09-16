package cmp.corporacionmineradelperu.modules.auth.service;

import cmp.corporacionmineradelperu.config.security.JwtService;
import cmp.corporacionmineradelperu.modules.auth.dto.AuthResponse;
import cmp.corporacionmineradelperu.modules.auth.dto.LoginRequest;
import cmp.corporacionmineradelperu.modules.auth.entity.Usuario;
import cmp.corporacionmineradelperu.modules.auth.mapper.UsuarioMapper;
import cmp.corporacionmineradelperu.modules.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    public AuthResponse login(LoginRequest request) {

        // 1. Si la contraseña es incorrecta, esto lanza una excepción automáticamente (403)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // 2. Si el código llega aquí, la contraseña es correcta y buscamos al usuario
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Generamos el token JWT con los datos del usuario
        String token = jwtService.generateToken(usuario);

        // 4. Usamos el Mapper para devolver el record AuthResponse
        return usuarioMapper.toAuthResponse(usuario, token);
    }
}

package cmp.corporacionmineradelperu.modules.personal.service;

import cmp.corporacionmineradelperu.modules.auth.entity.Rol;
import cmp.corporacionmineradelperu.modules.auth.entity.Usuario;
import cmp.corporacionmineradelperu.modules.auth.repository.UsuarioRepository;
import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioRequest;
import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioResponse;
import cmp.corporacionmineradelperu.modules.personal.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodosLosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponse registrarEspecialista(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado en el sistema");
        }

        String claveEncriptada = passwordEncoder.encode(request.password());

        Usuario nuevoUsuario = usuarioMapper.toUsuario(request, claveEncriptada, Rol.ESPECIALISTA);

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return usuarioMapper.toUsuarioResponse(usuarioGuardado);
    }

    @Transactional
    public void cambiarEstado(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Bloqueo de seguridad: Evita desactivar al SuperAdmin
        if (usuario.getRol() == Rol.SUPERADMIN) {
            throw new IllegalStateException("Acción denegada: No se puede desactivar al Super Administrador");
        }

        usuario.setActivo(!usuario.isActivo());
        usuarioRepository.save(usuario);
    }
}

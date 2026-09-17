package cmp.corporacionmineradelperu.modules.personal.controller;

import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioRequest;
import cmp.corporacionmineradelperu.modules.personal.dto.UsuarioResponse;
import cmp.corporacionmineradelperu.modules.personal.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosLosUsuarios());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> registrarEspecialista(@Valid  @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.registrarEspecialista(request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable UUID id) {
        usuarioService.cambiarEstado(id);
        return ResponseEntity.noContent().build();
    }
}

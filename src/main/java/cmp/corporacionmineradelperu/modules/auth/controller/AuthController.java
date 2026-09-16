package cmp.corporacionmineradelperu.modules.auth.controller;

import cmp.corporacionmineradelperu.modules.auth.dto.AuthResponse;
import cmp.corporacionmineradelperu.modules.auth.dto.LoginRequest;
import cmp.corporacionmineradelperu.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

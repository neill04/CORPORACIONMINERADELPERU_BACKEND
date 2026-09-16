package cmp.corporacionmineradelperu.config;

import cmp.corporacionmineradelperu.modules.auth.entity.Rol;
import cmp.corporacionmineradelperu.modules.auth.entity.Usuario;
import cmp.corporacionmineradelperu.modules.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.count() == 0) {

            Usuario superAdmin = Usuario.builder()
                    .nombres("Neill")
                    .apellidos("Olazabal")
                    .email("admin@cmp.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.SUPERADMIN)
                    .build();

            usuarioRepository.save(superAdmin);

            System.out.println("==========================================================");
            System.out.println("SEEDER EJECUTADO: Cuenta SUPERADMIN creada con éxito.");
            System.out.println("Email: admin@cmp.com");
            System.out.println("Password: admin123");
            System.out.println("==========================================================");
        }
    }
}

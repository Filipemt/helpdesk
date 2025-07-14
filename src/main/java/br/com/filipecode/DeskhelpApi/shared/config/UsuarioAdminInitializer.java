package br.com.filipecode.DeskhelpApi.shared.config;

import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UsuarioAdminInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void criarDefaultAdmin() {
        // Usuário de teste para desenvolvimento

        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();

            admin.setNome("admin");
            admin.setEmail("admin@gmail.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setDepartamento("Administração Sistema");
            admin.setCargo("Administrador");

            usuarioRepository.save(admin);
            System.out.println("Usuário ADMIN criado com sucesso");
        } else {
            System.out.println("Usuário ADMIN já existe. Nenhuma ação necessária");
        }
    }
}

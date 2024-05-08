package com.example.loginback.user.admin;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.loginback.user.Role;
import com.example.loginback.user.User;
import com.example.loginback.user.UserRepository;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminIfNotExist() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("strongPassword")) // Encriptar la contraseña
                    .firstname("Admin")
                    .lastname("Administrator")
                    .role(Role.ADMIN) // Asignar el rol ADMIN
                    .build();

            userRepository.save(admin); // Guardar el administrador
        }
    }
}
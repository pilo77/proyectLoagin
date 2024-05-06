package com.example.loginback.Auth;
import com.example.loginback.jwt.JwtService;
import com.example.loginback.user.Role;
import com.example.loginback.user.User;
import com.example.loginback.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println(request.getUsername()+ request.getPassword());
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        System.out.println(user);
        if (user.getRole().equals("ADMIN")){

        }else {
            System.out.println(user.getRole());
            return AuthResponse.builder()
                    .token(token)
                    .build();

        }


        return null;
    }


    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}
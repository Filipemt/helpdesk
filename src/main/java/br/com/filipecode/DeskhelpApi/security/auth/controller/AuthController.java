package br.com.filipecode.DeskhelpApi.security.auth.controller;

import br.com.filipecode.DeskhelpApi.security.auth.dto.LoginRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
            loginRequestDTO.email(), loginRequestDTO.senha()
        );

        authenticationManager.authenticate(authenticationToken);
        return ResponseEntity.ok().build();
    }
}

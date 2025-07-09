package br.com.filipecode.DeskhelpApi.shared.handler;

import br.com.filipecode.DeskhelpApi.shared.dto.ErroPadronizadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ErroPadronizadoDTO erro = new ErroPadronizadoDTO(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Não autenticado",
                "Você precisa estar autenticado para acessar este recurso",
                request.getRequestURI(),
                Instant.now().toString()
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String json = objectMapper.writeValueAsString(erro);
        response.getWriter().write(json);
    }
}

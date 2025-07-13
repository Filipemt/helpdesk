package br.com.filipecode.DeskhelpApi.shared.handler;

import br.com.filipecode.DeskhelpApi.shared.dto.ErroPadronizadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ErroPadronizadoDTO erro = new ErroPadronizadoDTO(
                HttpServletResponse.SC_FORBIDDEN,
                "Acesso negado",
                "Você não tem permissão para acessar esse recurso",
                request.getRequestURI(),
                Instant.now().toString()
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        String json = objectMapper.writeValueAsString(erro);
        response.getWriter().write(json);

    }
}

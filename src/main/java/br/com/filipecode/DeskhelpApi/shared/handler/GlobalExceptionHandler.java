package br.com.filipecode.DeskhelpApi.shared.handler;

import br.com.filipecode.DeskhelpApi.shared.dto.ErroPadronizadoDTO;
import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RequisicaoInvalidadeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

// todo: Tratar erro quando o Json body é mal formatado, por exemplo a virgula
//  {
//    "nome": "teste",
//    "senha": "teste",
//    "email": "teste@gmail.com", <-
//  }

// todo: tratar erro quando alguém tenta acessar um endpoint com um método HTTP não permitido

// todo: quando um parâmetro obrigatório não é informado (ex: ?id=).

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleRegistroDuplicado(RegistroDuplicadoException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro(HttpStatus.CONFLICT, "Registro duplicado", exception.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro(HttpStatus.NOT_FOUND, "Registro não encontrado", exception.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleRequisicaoInvalida(RequisicaoInvalidadeException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro(HttpStatus.BAD_REQUEST, "Requisição inválida", exception.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String campo = error.getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        String mensagemFormatada = erros.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("Campos inválidos");

        ErroPadronizadoDTO erroDTO = erro(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                mensagemFormatada,
                request
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
    }

    private ErroPadronizadoDTO erro(HttpStatus status, String titulo, String mensagem, HttpServletRequest request) {
        return new ErroPadronizadoDTO(
                status.value(),
                titulo,
                mensagem,
                request.getRequestURI(),
                Instant.now().toString()
        );
    }
}

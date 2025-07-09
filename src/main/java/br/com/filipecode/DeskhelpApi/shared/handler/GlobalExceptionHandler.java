package br.com.filipecode.DeskhelpApi.shared.handler;

import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RequisicaoInvalidadeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleRegistroDuplicado(RegistroDuplicadoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleRequisicaoInvalida(RequisicaoInvalidadeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String campo = error.getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        return ResponseEntity.badRequest().body(erros);
    }
}

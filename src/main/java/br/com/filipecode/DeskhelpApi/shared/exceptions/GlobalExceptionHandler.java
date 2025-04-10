package br.com.filipecode.DeskhelpApi.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}

package br.com.filipecode.DeskhelpApi.shared.handler;

import br.com.filipecode.DeskhelpApi.shared.dto.ErroPadronizadoDTO;
import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RequisicaoInvalidadeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleRegistroDuplicado(RegistroDuplicadoException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro(HttpStatus.CONFLICT,
                        "Registro duplicado",
                        exception.getMessage(),
                        request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro(HttpStatus.NOT_FOUND,
                        "Registro não encontrado",
                        exception.getMessage(),
                        request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleRequisicaoInvalida(RequisicaoInvalidadeException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro(HttpStatus.BAD_REQUEST,
                        "Requisição inválida",
                        exception.getMessage(),
                        request));
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

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(erro(HttpStatus.BAD_REQUEST,
                        "Erro de formatação JSON",
                        "Não foi possível interpretar o corpo da requisição. Verifique se o JSON está corretamente estruturado.", request));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(erro(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        "Método HTTP não permitido",
                        "O método " + exception.getMethod() + " não é suportado para este endpoint.",
                        request
                ));
    }

    @ExceptionHandler
    public ResponseEntity<ErroPadronizadoDTO> handleIllegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro(HttpStatus.BAD_REQUEST,
                        "Parâmetro obrigatório ausente",
                        exception.getMessage(),
                        request));
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

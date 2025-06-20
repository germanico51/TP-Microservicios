package com.todocodeacademy.ventas_service.exception; // El nuevo paquete para tus manejadores de excepciones

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException; // Importa ResponseStatusException
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Esta anotación indica que la clase manejará excepciones de forma global
public class GlobalExceptionHandler {

    /**
     * Este método se encarga de interceptar y manejar las ResponseStatusException
     * que son lanzadas en cualquier parte de tu aplicación (controladores, servicios, etc.).
     * Proporciona un formato de respuesta de error consistente y expone el mensaje personalizado.
     *
     * @param ex La instancia de ResponseStatusException capturada.
     * @return Una ResponseEntity que contiene un mapa con los detalles del error
     * y el HttpStatus correspondiente a la excepción.
     */

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", ex.getStatusCode().value());
        if (ex.getStatusCode() instanceof HttpStatus) {
            errorDetails.put("error", ((HttpStatus) ex.getStatusCode()).getReasonPhrase());
        } else {
            errorDetails.put("error", "Error Desconocido");
        }
        errorDetails.put("message", ex.getReason());
        errorDetails.put("path", "/ventas");

        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }

}
package com.aluracursos.Desafio_ForoHub.infra.security.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String mensaje) {
        super(mensaje);
    }
}

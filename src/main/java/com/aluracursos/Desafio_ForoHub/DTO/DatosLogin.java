package com.aluracursos.Desafio_ForoHub.DTO;
import jakarta.validation.constraints.NotBlank;

public record DatosLogin (
        @NotBlank String correoElectronico,
        @NotBlank String contrasena

) {
        }
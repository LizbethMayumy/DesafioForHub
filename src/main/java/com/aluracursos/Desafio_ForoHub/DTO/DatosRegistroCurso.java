package com.aluracursos.Desafio_ForoHub.DTO;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre,
        @NotBlank String categoria) {
}

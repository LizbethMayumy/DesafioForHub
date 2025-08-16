package com.aluracursos.Desafio_ForoHub.DTO;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
            @NotBlank String titulo,
            @NotBlank String mensaje
    ) {}



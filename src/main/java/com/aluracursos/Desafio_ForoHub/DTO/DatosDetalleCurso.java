package com.aluracursos.Desafio_ForoHub.DTO;

import com.aluracursos.Desafio_ForoHub.Entidades.Curso;

public record DatosDetalleCurso(Long id,
                                String nombre,
                                String categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
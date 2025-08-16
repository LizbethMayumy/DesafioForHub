package com.aluracursos.Desafio_ForoHub.DTO;

import com.aluracursos.Desafio_ForoHub.Entidades.Topico;

import java.time.LocalDateTime;

public record DatosTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String curso
) {
    public DatosTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getCurso().getNombre()
        );
    }
}

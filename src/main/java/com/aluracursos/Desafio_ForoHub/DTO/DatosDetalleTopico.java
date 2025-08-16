package com.aluracursos.Desafio_ForoHub.DTO;

import com.aluracursos.Desafio_ForoHub.Entidades.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        String status

) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
//                topico.getAutor(),
//                topico.getCurso()
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getStatus().toString()
        );
    }
}

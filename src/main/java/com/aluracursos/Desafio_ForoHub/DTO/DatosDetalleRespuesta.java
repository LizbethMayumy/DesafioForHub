package com.aluracursos.Desafio_ForoHub.DTO;


import com.aluracursos.Desafio_ForoHub.Entidades.Respuesta;

public record DatosDetalleRespuesta(Long id,
                                    String mensaje,
                                    String tituloTopico,
                                    String autor,
                                    Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion()
        );
    }
}
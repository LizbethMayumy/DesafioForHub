package com.aluracursos.Desafio_ForoHub.DTO;

import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre()
        );
    }
}
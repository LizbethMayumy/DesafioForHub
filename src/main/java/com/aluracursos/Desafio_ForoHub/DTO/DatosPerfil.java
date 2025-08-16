package com.aluracursos.Desafio_ForoHub.DTO;

import com.aluracursos.Desafio_ForoHub.Entidades.Perfil;

public record DatosPerfil(Long id, String nombre) {

    public DatosPerfil(Perfil perfil) {
        this (
                perfil.getId(),
                perfil.getNombre()
        );
    }
}

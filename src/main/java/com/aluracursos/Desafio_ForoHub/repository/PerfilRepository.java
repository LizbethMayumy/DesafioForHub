package com.aluracursos.Desafio_ForoHub.repository;

import com.aluracursos.Desafio_ForoHub.Entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNombre(String perfil);
}

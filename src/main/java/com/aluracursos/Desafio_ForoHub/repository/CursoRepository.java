package com.aluracursos.Desafio_ForoHub.repository;

import com.aluracursos.Desafio_ForoHub.Entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNombreAndCategoria(String nombre, String categoria);
}

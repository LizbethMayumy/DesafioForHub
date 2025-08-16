package com.aluracursos.Desafio_ForoHub.repository;

import com.aluracursos.Desafio_ForoHub.Entidades.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findByCursoNombre(String nombreCurso, Pageable paginacion);
}

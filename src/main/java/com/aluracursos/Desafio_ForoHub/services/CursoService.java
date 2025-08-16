package com.aluracursos.Desafio_ForoHub.services;

import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleCurso;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroCurso;
import com.aluracursos.Desafio_ForoHub.Entidades.Curso;
import com.aluracursos.Desafio_ForoHub.repository.CursoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso registrar(DatosRegistroCurso datos) {
        if (cursoRepository.existsByNombreAndCategoria(datos.nombre(), datos.categoria())) {
            throw new ValidationException("No existen cursos con el nombre y categoría.");
        }
        return new DatosDetalleCurso(cursoRepository.save(new Curso(datos)));
    }
}


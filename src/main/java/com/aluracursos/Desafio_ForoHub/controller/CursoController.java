package com.aluracursos.Desafio_ForoHub.controller;


import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleCurso;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroCurso;
import com.aluracursos.Desafio_ForoHub.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    private static final String ROLE_ADMIN = "ROLE_admin";
    @PreAuthorize("hasRole('admin')")
    @Transactional
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroCurso datos) {
        DatosDetalleCurso detalleCurso = cursoService.registrar(datos);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(detalleCurso.id())
                .toUri();

        return ResponseEntity.created(location).body(detalleCurso);
    }
}

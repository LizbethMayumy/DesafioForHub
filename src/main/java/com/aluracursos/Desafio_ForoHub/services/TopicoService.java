package com.aluracursos.Desafio_ForoHub.services;

import com.aluracursos.Desafio_ForoHub.DTO.DatosActualizarTopico;
import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleTopico;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroTopico;
import com.aluracursos.Desafio_ForoHub.Entidades.Curso;
import com.aluracursos.Desafio_ForoHub.infra.security.exception.ValidationException;
import com.aluracursos.Desafio_ForoHub.Entidades.Topico;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import com.aluracursos.Desafio_ForoHub.repository.CursoRepository;
import com.aluracursos.Desafio_ForoHub.repository.TopicoRepository;
import com.aluracursos.Desafio_ForoHub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;


    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {

        validarTituloMensaje(datos);

        var autor = obtenerAutorSiExiste(datos);
        var curso = obtenerCursoSiExiste(datos);

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), LocalDateTime.now(), Boolean.TRUE, autor,
                curso, new ArrayList<>());

        return new DatosDetalleTopico(topicoRepository.save(topico));
    }

    public Page<DatosDetalleTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }

    public Page<DatosDetalleTopico> listarTopicosPorNombreCurso(String nombreCurso, Pageable paginacion) {
        return topicoRepository.findByCursoNombre(nombreCurso, paginacion).map(DatosDetalleTopico::new);
    }

    public DatosDetalleTopico detallarPorId(Long idTopico) {
        return new DatosDetalleTopico(topicoRepository.getReferenceById(idTopico));
    }

    @Transactional
    public DatosDetalleTopico actualizar(Long id, DatosActualizarTopico datos) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado."));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());


        return new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getStatus().toString()
        );
    }


    public void eliminar(Long id) {
        var topicoBuscado = obtenerTopicoSiExiste(id);
        topicoRepository.deleteById(topicoBuscado.getId());
    }

    private Topico obtenerTopicoSiExiste(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tópico no existe"));
    }

    private Usuario obtenerAutorSiExiste(DatosRegistroTopico topico) {
        return usuarioRepository.findById(topico.autorId())
                .orElseThrow(() -> new ValidationException("No existe el autor"));
    }

    private Curso obtenerCursoSiExiste(DatosRegistroTopico topico) {
        return cursoRepository.findById(topico.cursoId())
                .orElseThrow(() -> new ValidationException("No existe el curso"));
    }

    private void validarTituloMensaje(DatosRegistroTopico topico) {
        if (topicoRepository.existsByTituloAndMensaje(topico.titulo(), topico.mensaje()))
            throw new ValidationException("Ya existe el tópico con el mismo título y mensaje");
    }
}

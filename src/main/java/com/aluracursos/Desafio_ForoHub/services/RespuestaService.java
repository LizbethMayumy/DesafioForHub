package com.aluracursos.Desafio_ForoHub.services;

import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleRespuesta;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroRespuesta;
import com.aluracursos.Desafio_ForoHub.Entidades.Respuesta;
import com.aluracursos.Desafio_ForoHub.Entidades.Topico;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import com.aluracursos.Desafio_ForoHub.infra.security.exception.ValidationException;
import com.aluracursos.Desafio_ForoHub.repository.RespuestaRepository;
import com.aluracursos.Desafio_ForoHub.repository.TopicoRepository;
import com.aluracursos.Desafio_ForoHub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RespuestaService {
    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos) {
        validarMensaje(datos);
        var topico = obtenerTopicoSiExiste(datos.topicoId());
        var autor = obtenerAutorSiExiste(datos);
        var solucion = datos.solucion() != null ? datos.solucion() : false;
        var respuesta = new Respuesta(null, datos.mensaje(), topico, LocalDateTime.now(), autor, solucion);
        return new DatosDetalleRespuesta(respuestaRepository.save(respuesta));
    }

    public Respuesta actualizar(Long id, DatosRegistroRespuesta respuesta) {
        validarMensajeAlActualizar(id, respuesta);
        var respuestaBuscada = obtenerRespuestaSiExiste(id);
        var autor = obtenerAutorSiExiste(respuesta);
        respuestaBuscada.actualizar(respuesta, autor, respuesta.solucion());
        return respuestaBuscada;
    }

    private Respuesta obtenerRespuestaSiExiste(Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe esta respuesta"));
    }

    private Topico obtenerTopicoSiExiste(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe este topico"));
    }

    private Usuario obtenerAutorSiExiste(DatosRegistroRespuesta respuesta) {
        return usuarioRepository.findById(respuesta.autorId())
                .orElseThrow(() -> new ValidationException("No existe este autor"));
    }

    private void validarMensaje(DatosRegistroRespuesta respuesta) {
        if (respuestaRepository.existsByMensajeAndTopicoId(respuesta.mensaje(), respuesta.topicoId()))
            throw new ValidationException("Ya existe una respuesta para este topico con la misma respuesta.");
    }

    private void validarMensajeAlActualizar(Long id, DatosRegistroRespuesta respuesta) {
        if (respuestaRepository.existsByMensajeAndTopicoIdAndIdNot(respuesta.mensaje(), respuesta.topicoId(), id))
            throw new ValidationException("Ya existe una respuesta con el mismo mensaje para este tópico");
    }
}

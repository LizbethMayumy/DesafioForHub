package com.aluracursos.Desafio_ForoHub.Entidades;

import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroRespuesta;
import com.aluracursos.Desafio_ForoHub.Entidades.Topico;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicoId")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId")
    private Usuario autor;

    private Boolean solucion;

    public void actualizar(DatosRegistroRespuesta respuesta, Usuario autor, Boolean solucion) {
        this.mensaje = respuesta.mensaje();
        this.autor = autor;
        this.solucion = solucion;
    }
}
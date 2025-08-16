package com.aluracursos.Desafio_ForoHub.Entidades;
import  com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroTopico;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cursoId")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuesta;

    public void actualizar(DatosRegistroTopico topico, Usuario autor, Curso curso) {
        titulo = topico.titulo();
        mensaje = topico.mensaje();
        this.autor = autor;
        this.curso = curso;
    }
}

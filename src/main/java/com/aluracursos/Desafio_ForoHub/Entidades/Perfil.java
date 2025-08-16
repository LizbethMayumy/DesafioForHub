package com.aluracursos.Desafio_ForoHub.Entidades;

import com.aluracursos.Desafio_ForoHub.DTO.DatosPerfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "perfil")
    private Set<Usuario> usuario = new HashSet<>();
    public Perfil(DatosPerfil datosPerfil) {
        id = datosPerfil.id();
        nombre = datosPerfil.nombre();
    }
}

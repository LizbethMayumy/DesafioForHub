package com.aluracursos.Desafio_ForoHub.Entidades;

import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correoElectronico")
    private String correoElectronico;

    @Column(name = "contrasena")
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarioPerfil",
            joinColumns = @JoinColumn(name = "usuarioId"),
            inverseJoinColumns = @JoinColumn(name = "perfilId"))
    private List<Perfil> perfil = new ArrayList<>();

    public Usuario(DatosRegistroUsuario datosRegistro, String contrasenaHash) {
        this.nombre = datosRegistro.nombre();
        this.correoElectronico = datosRegistro.correoElectronico();
        this.contrasena = contrasenaHash;
        this.perfil.addAll(
                datosRegistro.perfil().stream()
                        .map(Perfil::new)
                        .toList()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfil.stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getNombre()))
                .toList();
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfil= perfiles;
    }

    public List<Perfil> getPerfiles() {
        return perfil;
    }
}

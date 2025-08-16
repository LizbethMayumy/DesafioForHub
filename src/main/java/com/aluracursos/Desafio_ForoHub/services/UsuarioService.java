package com.aluracursos.Desafio_ForoHub.services;

import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleUsuario;
import com.aluracursos.Desafio_ForoHub.DTO.DatosPerfil;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroUsuario;
import com.aluracursos.Desafio_ForoHub.Entidades.Perfil;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import com.aluracursos.Desafio_ForoHub.infra.security.exception.ValidationException;
import com.aluracursos.Desafio_ForoHub.repository.PerfilRepository;
import com.aluracursos.Desafio_ForoHub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByCorreoElectronico(datos.correoElectronico()))
            throw new ValidationException("El correo ya ha sido registrado.");

        List<Perfil> perfilesAsignados = new ArrayList<>();

        if (datos.perfil() == null || datos.perfil().isEmpty()) {
            var perfilRegistrado = perfilRepository.findByNombre("registrado");
            if (perfilRegistrado != null) {
                perfilesAsignados.add(perfilRegistrado);
            } else {
                var perfilParticular = perfilRepository.findByNombre("particular");
                if (perfilParticular != null) {
                    perfilesAsignados.add(perfilParticular);
                } else {
                    perfilesAsignados.add(perfilRepository.save(new Perfil(new DatosPerfil(null, "particular"))));
                }
            }
        } else {
            for (DatosPerfil datosPerfil : datos.perfil()) {
                var perfil = perfilRepository.findById(datosPerfil.id())
                        .orElseThrow(() -> new ValidationException("Perfíl inválido '".concat(datosPerfil.nombre()).concat("'")));
                perfilesAsignados.add(perfil);
            }
        }

       // var contrasenaHash = passwordEncoder.encode(datos.contrasena());
        var usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setPerfiles(perfilesAsignados);
        usuario.setCorreoElectronico(datos.correoElectronico());
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        usuarioRepository.save(usuario);
        return new DatosDetalleUsuario(usuario.getId(), usuario.getCorreoElectronico());
    }

}
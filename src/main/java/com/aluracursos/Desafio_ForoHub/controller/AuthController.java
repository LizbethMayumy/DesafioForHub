//package com.aluracursos.Desafio_ForoHub.controller;
//
//import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleUsuario;
//import com.aluracursos.Desafio_ForoHub.DTO.DatosLogin;
//import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroUsuario;
//import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
//import com.aluracursos.Desafio_ForoHub.infra.security.TokenService;
//import com.aluracursos.Desafio_ForoHub.services.UsuarioService;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    // Registro de usuario
//    @Transactional
//    @PostMapping("/register")
//    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
//        DatosDetalleUsuario detalleUsuario = usuarioService.registrar(datos);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(detalleUsuario.id())
//                .toUri();
//
//        return ResponseEntity.created(location).body(detalleUsuario);
//    }
//
//    // Login de usuario
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Valid DatosLogin datosLogin) {
//        try {
//            Authentication authToken = new UsernamePasswordAuthenticationToken(
//                    datosLogin.correoElectronico(),
//                    datosLogin.contrasena()
//            );
//            System.out.println("Intentando autenticar: " + datosLogin.correoElectronico());
//
//
//            Authentication authentication = authenticationManager.authenticate(authToken);
//            Usuario usuario = (Usuario) authentication.getPrincipal();
//            String jwt = tokenService.generarToken(usuario);
//
//            return ResponseEntity.ok(new DatosJWT(jwt));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(401).body("Error de autenticación: " + e.getMessage());
//        }
//    }
//
//    // DTO para la respuesta del login
//    public record DatosJWT(String token) {}
//}


package com.aluracursos.Desafio_ForoHub.controller;

import com.aluracursos.Desafio_ForoHub.DTO.DatosDetalleUsuario;
import com.aluracursos.Desafio_ForoHub.DTO.DatosLogin;
import com.aluracursos.Desafio_ForoHub.DTO.DatosRegistroUsuario;
import com.aluracursos.Desafio_ForoHub.Entidades.Usuario;
import com.aluracursos.Desafio_ForoHub.infra.security.TokenService;
import com.aluracursos.Desafio_ForoHub.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        DatosDetalleUsuario detalleUsuario = usuarioService.registrar(datos);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(detalleUsuario.id())
                .toUri();

        return ResponseEntity.created(location).body(detalleUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DatosLogin datosLogin) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosLogin.correoElectronico(),
                datosLogin.contrasena()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String jwt = tokenService.generarToken(usuario);

        return ResponseEntity.ok(new DatosJWT(jwt));
    }

    public record DatosJWT(String token) {}
}


package com.petscare.auth.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.petscare.auth.model.Usuario;
import com.petscare.auth.service.UsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Registro de usuario
     * Respuestas:
     *  - 201 Created + Location: /api/registro/usuarios/{id} (cuando se crea)
     *  - 409 Conflict: si el correo ya está registrado
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        String resultado = usuarioService.registrarUsuario(usuario);

        if ("Correo ya registrado".equalsIgnoreCase(resultado)) {
            // Email existente -> 409 Conflict
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resultado);
        }

        // Asumiendo que JPA asignó el ID al objeto 'usuario' tras persistir
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(location).body("Registro exitoso");
    }

    /**
     * Inicio de sesión
     * Respuestas:
     *  - 200 OK: credenciales válidas
     *  - 401 Unauthorized: credenciales inválidas
     */
    @PostMapping("/inicio-sesion") // <-- usa minúsculas y guion
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        if (!autenticado) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en autenticación");
        }
        return ResponseEntity.ok("Autenticación exitosa");
    }
}

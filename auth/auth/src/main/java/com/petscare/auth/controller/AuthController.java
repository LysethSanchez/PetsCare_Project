package com.petscare.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petscare.auth.model.Usuario;
import com.petscare.auth.service.UsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para registro
    @PostMapping("/registro")
    public String registrarUsuario(@RequestBody Usuario usuario) {
        System.out.println("Datos recibidos para registro: " + usuario.getEmail() + " / " + usuario.getPassword());
        return usuarioService.registrarUsuario(usuario);
    }

    // Endpoint para inicio de sesión
    @PostMapping("/inicio")
    public String login(@RequestBody Usuario usuario) {
        System.out.println("Intento de login con: " + usuario.getEmail());
        boolean autenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        return autenticado ? "Autenticación exitosa" : "Error en autenticación";
    }

}

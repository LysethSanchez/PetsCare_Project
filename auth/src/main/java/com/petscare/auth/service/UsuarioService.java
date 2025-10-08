package com.petscare.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Usuario;
import com.petscare.auth.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String registrarUsuario(Usuario usuario) {
        // Verifica si el correo ya existe
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return "Correo ya registrado";
        }
        System.out.println("Guardando usuario con email: " + usuario.getEmail() + " y password: " + usuario.getPassword());
        usuarioRepository.save(usuario);
        return "Registro exitoso";
    }

    public boolean autenticarUsuario(String email, String password) {
        return usuarioRepository.findByEmail(email)
            .map(usuario -> {
                String storedPassword = usuario.getPassword();
                System.out.println("Comparando contraseña: " + password + " con la almacenada: " + storedPassword);
                return storedPassword != null && storedPassword.equals(password);
            })
            .orElse(false);
    }

}

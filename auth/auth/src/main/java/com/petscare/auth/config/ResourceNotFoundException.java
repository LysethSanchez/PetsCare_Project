package com.petscare.auth.config;

/**
 * Excepción personalizada para indicar que un recurso no fue encontrado.
 * Se usará en los controladores para disparar un 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

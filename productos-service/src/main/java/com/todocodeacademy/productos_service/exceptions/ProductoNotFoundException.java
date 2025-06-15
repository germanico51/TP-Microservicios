package com.todocodeacademy.productos_service.exceptions;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(Long id) {
        super("El pruducto con el id especificado no fue encontrado");
    }
}

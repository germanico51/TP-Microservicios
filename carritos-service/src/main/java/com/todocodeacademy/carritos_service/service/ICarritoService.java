package com.todocodeacademy.carritos_service.service;

import com.todocodeacademy.carritos_service.model.Carrito;

public interface ICarritoService {
    void save(Carrito carrito);

    void addProduct(Long idCarrito, Long idProducto);

    Carrito findCarritoById(Long idCarrito);
}

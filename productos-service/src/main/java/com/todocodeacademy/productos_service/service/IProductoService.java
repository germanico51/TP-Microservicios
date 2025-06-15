package com.todocodeacademy.productos_service.service;

import com.todocodeacademy.productos_service.model.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> getProductos();

    Producto getProductById(Long id);

    void save(Producto prod);
}

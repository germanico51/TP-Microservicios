package com.todocodeacademy.carritos_service.service;

import com.todocodeacademy.carritos_service.dto.CarritoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;

import java.util.List;

public interface ICarritoService {
    void save(Carrito carrito);

    void addProduct(Long idCarrito, Long idProducto);

    void deleteProduct(Long idCarrito, Long idProducto);

    Carrito findCarritoById(Long idCarrito);

    CarritoDTO getCarritoDTO(Long idCarrito);

    List<CarritoDTO> getAllCarritos();
}

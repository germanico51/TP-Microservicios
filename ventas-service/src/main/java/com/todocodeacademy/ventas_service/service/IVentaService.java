package com.todocodeacademy.ventas_service.service;

import com.todocodeacademy.ventas_service.dto.ProductoDTO;
import com.todocodeacademy.ventas_service.model.Venta;

import java.util.List;

public interface IVentaService {
    void save(Long idCarrito);

    Venta findVentaById(Long id);

    List<ProductoDTO> getProductsByVenta(Long id);

    Double getMontoVenta(Long id);

    List<Venta> findAllVentas();
}

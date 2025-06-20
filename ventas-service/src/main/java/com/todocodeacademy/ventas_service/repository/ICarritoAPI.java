package com.todocodeacademy.ventas_service.repository;

import com.todocodeacademy.ventas_service.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carritos-service")
public interface ICarritoAPI {

    @GetMapping("/carritos/{idCarrito}")
    public CarritoDTO getCarritoById(@PathVariable Long idCarrito);
}

package com.todocodeacademy.carritos_service.repository;

import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "productos-service")
public interface IProductosAPI {
    @GetMapping("/productos/{idProd}")
    public ProductoDTO getProductoById(@PathVariable("idProd") Long idProd);
}

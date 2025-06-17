package com.todocodeacademy.ventas_service.controller;

import com.todocodeacademy.ventas_service.dto.ProductoDTO;
import com.todocodeacademy.ventas_service.model.Venta;
import com.todocodeacademy.ventas_service.service.IVentaService;
import com.todocodeacademy.ventas_service.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    IVentaService ventaService;

    @PostMapping("/crear")
    public String createVenta(@RequestParam Long idCarrito){
        ventaService.save(idCarrito);
        return "Venta creada";
    }

    @GetMapping("/{id}")
    public Venta getVentaById(@RequestParam Long id){
        return ventaService.findVentaById(id);
    }

    @GetMapping("/{id}/monto")
    public Double getMontoVenta(@RequestParam Long id){
        return ventaService.getMontoVenta(id);
    }

    @GetMapping("/{id}/productos")
    public List<ProductoDTO> getListaProductos(@RequestParam Long id){
        return ventaService.getProductsByVenta(id);
    }
}

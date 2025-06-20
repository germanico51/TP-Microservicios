package com.todocodeacademy.ventas_service.controller;

import com.todocodeacademy.ventas_service.dto.ProductoDTO;
import com.todocodeacademy.ventas_service.model.Venta;
import com.todocodeacademy.ventas_service.service.IVentaService;
import com.todocodeacademy.ventas_service.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final IVentaService ventaService;
    public VentasController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }
    @PostMapping
    public ResponseEntity<String> createVenta(@RequestParam Long idCarrito) {
        ventaService.save(idCarrito);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada");
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getVentas() {
        return ResponseEntity.ok(ventaService.findAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.findVentaById(id));
    }

    @GetMapping("/{id}/monto")
    public ResponseEntity<Double> getMontoVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getMontoVenta(id));
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<ProductoDTO>> getListaProductos(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getProductsByVenta(id));
    }
}

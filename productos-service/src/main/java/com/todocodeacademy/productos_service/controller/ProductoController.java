package com.todocodeacademy.productos_service.controller;

import com.todocodeacademy.productos_service.model.Producto;
import com.todocodeacademy.productos_service.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService prodService;

    public ProductoController(IProductoService prodService) {
        this.prodService = prodService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProducts() {
        return ResponseEntity.ok(prodService.getProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(prodService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Producto prod) {
        prodService.save(prod);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }
}

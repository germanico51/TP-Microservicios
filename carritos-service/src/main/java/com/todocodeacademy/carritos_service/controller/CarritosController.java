package com.todocodeacademy.carritos_service.controller;

import com.todocodeacademy.carritos_service.dto.CarritoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;
import com.todocodeacademy.carritos_service.service.ICarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritosController {
    ICarritoService carritoService;
    public CarritosController(ICarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<String> createCarrito(@RequestBody Carrito carrito){
        Carrito nuevoCarrito = new Carrito();
        carritoService.save(nuevoCarrito);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nuevo carrito creado con ID: " + nuevoCarrito.getId());
    }

    @GetMapping
    public ResponseEntity<List<CarritoDTO>> getCarritos(){
        return ResponseEntity.ok(carritoService.getAllCarritos());
    }
    @GetMapping("/{idCarrito}")
    public ResponseEntity<CarritoDTO> getCarritoById(@PathVariable Long idCarrito){
        return ResponseEntity.ok(carritoService.getCarritoDTO(idCarrito));
    }
    @PostMapping("/{idCarrito}/productos")
    public ResponseEntity<String> addProduct(@PathVariable Long idCarrito,@RequestParam Long idProducto){
        carritoService.addProduct(idCarrito, idProducto);
        return ResponseEntity.ok("producto agregado");
    }
    @DeleteMapping("/{idCarrito}/productos/{idProducto}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long idCarrito,@PathVariable Long idProducto){
        carritoService.deleteProduct(idCarrito, idProducto);
        return ResponseEntity.ok("producto eliminado");
    }
}

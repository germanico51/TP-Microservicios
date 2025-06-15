package com.todocodeacademy.carritos_service.controller;

import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;
import com.todocodeacademy.carritos_service.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    ICarritoService carritoService;

    @PostMapping("/create")
    public String createCarrito(@RequestBody Carrito carrito){
        carritoService.save(carrito);
        return "Nuevo carrito creado";
    }

    @GetMapping("/{idCarrito}")
    public Carrito getCarrito(@PathVariable Long idCarrito){
        return carritoService.findCarritoById(idCarrito);
    }

    @PostMapping("/{idCarrito}/items")
    public String addProduct(@PathVariable Long idCarrito,@RequestParam Long idProducto,@RequestParam int cantidad){
        carritoService.addProduct(idCarrito, idProducto);
        return "producto agregado";
    }
}

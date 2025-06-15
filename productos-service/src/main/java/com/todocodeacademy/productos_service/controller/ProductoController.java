package com.todocodeacademy.productos_service.controller;

import com.todocodeacademy.productos_service.model.Producto;
import com.todocodeacademy.productos_service.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    IProductoService prodService;
    @GetMapping("/get")
    public List<Producto> getProducts(){
        return prodService.getProductos();
    }
    @GetMapping("/get/{id}")
    public Producto getProductById(@PathVariable Long id){
        return prodService.getProductById(id);
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Producto prod){
        prodService.save(prod);
        return "producto creado";
    }
}

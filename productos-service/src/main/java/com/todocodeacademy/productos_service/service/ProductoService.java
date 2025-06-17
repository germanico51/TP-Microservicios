package com.todocodeacademy.productos_service.service;

import com.todocodeacademy.productos_service.model.Producto;
import com.todocodeacademy.productos_service.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository productRepo;
    @Override
    public List<Producto> getProductos() {
        return productRepo.findAll();
    }

    @Override
    public Producto getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    @Override
    public void save(Producto prod) {
        productRepo.save(prod);
    }
}

package com.todocodeacademy.productos_service.service;

import com.todocodeacademy.productos_service.exceptions.ProductoNotFoundException;
import com.todocodeacademy.productos_service.model.Producto;
import com.todocodeacademy.productos_service.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productRepo.findById(id).orElseThrow(()-> new ProductoNotFoundException(id));
    }

    @Override
    public void save(Producto prod) {
        productRepo.save(prod);
    }
}

package com.todocodeacademy.carritos_service.service;

import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;
import com.todocodeacademy.carritos_service.repository.ICarritoRepository;
import com.todocodeacademy.carritos_service.repository.IProductosAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarritoService implements ICarritoService {
    @Autowired
    ICarritoRepository carritoRepo;

    @Autowired
    IProductosAPI apiProd;

    @Override
    public void save(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    @Override
    public void addProduct(Long idCarrito, Long idProducto) {
        Carrito carrito = carritoRepo.findById(idCarrito).orElse(null);

        if(carrito!=null){
            //usar feign
            ProductoDTO dtoProd = apiProd.getProductoById(idProducto);
            List<ProductoDTO> listaProds = carrito.getProductosList();
            listaProds.add(dtoProd);
            carrito.setProductosList(listaProds);
            save(carrito);
        }


    }
    @Override
    public Carrito findCarritoById(Long idCarrito) {
        return carritoRepo.findById(idCarrito).orElseThrow(()->new RuntimeException("carrito no encontrado"));
    }


}

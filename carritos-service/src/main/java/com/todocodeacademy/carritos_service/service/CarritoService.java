package com.todocodeacademy.carritos_service.service;

import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;
import com.todocodeacademy.carritos_service.repository.ICarritoRepository;
import com.todocodeacademy.carritos_service.repository.IProductosAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Carrito carrito = this.findCarritoById(idCarrito);
        ProductoDTO dtoProd = apiProd.getProductoById(idProducto);
        carrito.getProductosList().add(dtoProd);
        carrito.setPrecioTotal(calcularPrecioTotalProductos(carrito.getProductosList()));
        save(carrito);
    }
    @Override
    public void deleteProduct(Long idCarrito, Long idProducto) {
        Carrito carrito = this.findCarritoById(idCarrito);

        //ProductoDTO dtoProd = apiProd.getProductoById(idProducto);
        carrito.getProductosList().removeIf(p->p.getIdProducto().equals(idProducto));
        carrito.setPrecioTotal(calcularPrecioTotalProductos(carrito.getProductosList()));
        save(carrito);

    }
    @Override
    public Carrito findCarritoById(Long idCarrito) {
        return carritoRepo.findById(idCarrito).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"carrito no encontrado"));
    }

    public Double calcularPrecioTotalProductos(List<ProductoDTO> listaprods){
        return listaprods.stream().mapToDouble(ProductoDTO::getPrecio).sum();
    }


}

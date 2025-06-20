package com.todocodeacademy.carritos_service.service;

import com.todocodeacademy.carritos_service.dto.CarritoDTO;
import com.todocodeacademy.carritos_service.dto.ProductoDTO;
import com.todocodeacademy.carritos_service.model.Carrito;
import com.todocodeacademy.carritos_service.repository.ICarritoRepository;
import com.todocodeacademy.carritos_service.repository.IProductosAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        ProductoDTO productoParaAgregar = getProductoById(idProducto);
        if (productoParaAgregar == null ||
                productoParaAgregar.getNombre().equals("Producto no disponible") || // Es el fallback
                productoParaAgregar.getPrecio() <= 0.0) { // O tiene un precio no válido

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo agregar el producto con ID " + idProducto +
                            ". Verifique que el producto exista y esté disponible.");
        }

        if (carrito.getProductoIds() == null) {
            carrito.setProductoIds(new ArrayList<>());
        }
        carrito.getProductoIds().add(idProducto);

        carrito.setPrecioTotal(calcularPrecioTotalProductos(carrito.getProductoIds()));
        save(carrito);
    }


    @Override
    public void deleteProduct(Long idCarrito, Long idProducto) {
        Carrito carrito = this.findCarritoById(idCarrito);
        if (carrito.getProductoIds() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El carrito no contiene productos.");
        }
        boolean removed = carrito.getProductoIds().removeIf(idProd -> idProd.equals(idProducto));
        if (removed) {
            carrito.setPrecioTotal(calcularPrecioTotalProductos(carrito.getProductoIds()));
            save(carrito);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto con ID " + idProducto + " no se encontró en el carrito " + idCarrito + ".");
        }
    }
    @Override
    public CarritoDTO getCarritoDTO(Long idCarrito) {
        Carrito carrito = findCarritoById(idCarrito);
        List<ProductoDTO> productos = carrito.getProductoIds().stream()
                .map(this::getProductoById)
                .toList();
        return new CarritoDTO(carrito.getId(), carrito.getPrecioTotal(), productos);
    }

    @Override
    public List<CarritoDTO> getAllCarritos() {
        List<Carrito> listaCarritos = carritoRepo.findAll(); // Obtiene todos los carritos de la DB

        // Mapea cada Carrito a un CarritoDTO, incluyendo los detalles de los productos
        return listaCarritos.stream()
                .map(carrito -> {
                    // Para cada carrito, obtenemos los detalles completos de sus productos
                    List<ProductoDTO> productosDetalles = new ArrayList<>();
                    if (carrito.getProductoIds() != null && !carrito.getProductoIds().isEmpty()) {
                        productosDetalles = carrito.getProductoIds().stream()
                                .map(this::getProductoById) // Reusa tu método existente para obtener ProductoDTO
                                .collect(Collectors.toList()); // O .toList() si usas Java 16+
                    }
                    // Crea un nuevo CarritoDTO con los datos del carrito y la lista de productos detallada
                    return new CarritoDTO(carrito.getId(), carrito.getPrecioTotal(), productosDetalles);
                })
                .collect(Collectors.toList()); // Recolecta todos los CarritoDTO en una lista
    }

    @Override
    public Carrito findCarritoById(Long idCarrito) {
        return carritoRepo.findById(idCarrito).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"carrito no encontrado"));
    }
    @CircuitBreaker(name = "productos-service", fallbackMethod = "fallbackGetProductById")
    @Retry(name= "productos-service")
    public ProductoDTO getProductoById(Long idProducto) {
        return apiProd.getProductoById(idProducto);
    }

    public ProductoDTO fallbackGetProductById(Long idProducto, Throwable t) {
        return new ProductoDTO(idProducto, "Producto no disponible", "N/A", 0.0);
    }
    public Double calcularPrecioTotalProductos(List<Long> listaprods){
        return listaprods.stream().map(this::getProductoById).mapToDouble(ProductoDTO::getPrecio).sum();
    }


}

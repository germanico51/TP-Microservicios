package com.todocodeacademy.ventas_service.service;

import com.todocodeacademy.ventas_service.dto.CarritoDTO;
import com.todocodeacademy.ventas_service.dto.ProductoDTO;
import com.todocodeacademy.ventas_service.model.Venta;
import com.todocodeacademy.ventas_service.repository.ICarritoAPI;
import com.todocodeacademy.ventas_service.repository.IVentaRepository;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService implements IVentaService {
    @Autowired
    IVentaRepository ventaRepo;
    @Autowired
    ICarritoAPI apiCarrito;
    @Override
    public void save(Long idCarrito) {
        CarritoDTO carrito = this.getCarritoById(idCarrito);
        if (carrito.getProductosList() == null || carrito.getProductosList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede registrar una venta con un carrito vacío. Por favor, agregue productos al carrito.");
        }
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setIdCarrito(carrito.getId());
        ventaRepo.save(venta);
    }
    @Override
    public Venta findVentaById(Long id) {
        return ventaRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
    }
    @Override
    public List<ProductoDTO> getProductsByVenta(Long id) {
        return this.getCarritoById(findVentaById(id).getIdCarrito()).getProductosList();
    }
    @Override
    public Double getMontoVenta(Long id) {
        return this.getCarritoById(findVentaById(id).getIdCarrito()).getPrecioTotal();
    }

    @Override
    public List<Venta> findAllVentas() {
        return ventaRepo.findAll();
    }

    @CircuitBreaker(name = "carritos-service", fallbackMethod = "fallbackGetCarritoById")
    @Retry(name = "carritos-service")
    public CarritoDTO getCarritoById(Long idCarrito) {
        try {
            return apiCarrito.getCarritoById(idCarrito);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado para esta venta");
        }
    }
    public CarritoDTO fallbackGetCarritoById(Long idCarrito, Throwable t){
        return new CarritoDTO(idCarrito,0.0, null);
    }


}
